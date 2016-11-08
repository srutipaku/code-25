/*
 * The scoring system for this challenge is binary. Your score is zero unless you pass all tests.

Given  strictly convex simple polygons and  ellipses on a plane, find any point lying in their intersection. Then print two lines of output, where the first line contains the point's  coordinate and the second line contains its coordinate. The point lying on the boundary of an ellipse or polygon is considered to be an inner point.

Input Format

The first line contains an integer, , denoting the number of polygons. 
The next set of lines defines  polygons, where each polygon  is described as follows:

The first line contains an integer, , denoting the number of vertices in polygon .
Each of the  subsequent lines contains two space-separated integers denoting the respective  and coordinates for one of polygon 's vertices. The list of vertices is given in counterclockwise order.
The next line contains an integer, , denoting the number of ellipses. 
Each of the  subsequent lines contains five space-separated integers denoting the respective values of , , , , and , which are the coordinates of the four focal points and the semi-major-axis for an Ellipse.
 */

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class GoodPoint {
	InputStream is;
	PrintWriter out;
	String INPUT = "";

	
	void solve()
	{
		int n = ni();
		int[][][] ps = new int[n][][];
		for(int i = 0;i < n;i++){
			int L = ni();
			ps[i] = new int[L][];
			for(int j = 0;j < L;j++){
				ps[i][j] = na(2);
			}
		}
		int m = ni();
		int[][] es = new int[m][];
		for(int i = 0;i < m;i++){
			es[i] = na(5);
		}
		double[][][][] ts = new double[m][][][];
		for(int i = 0;i < m;i++){
			double r = Point2D.distance(es[i][0], es[i][1], es[i][2], es[i][3]);
			if(r < 1e-13){
				ts[i] = trans(es[i][0], es[i][1], es[i][0] + es[i][4], es[i][1], 0, 0, es[i][4], 0);
			}else{
				ts[i] = trans(es[i][0], es[i][1], es[i][2], es[i][3], -r/2, 0, r/2, 0);
			}
		}
		
		double[] pt = {10000., 10000.};
		double alpha = 10000;
		for(int rep = 0;rep < 10000;rep++){
			double[] v = new double[2];
			for(int i = 0;i < n;i++){
				moveP(pt, ps[i], v);

			}
			for(int i = 0;i < m;i++){
				moveE(pt, es[i], v, ts[i]);

			}
			double norm = Math.sqrt(v[0]*v[0]+v[1]*v[1]);
			if(norm < 1e-13)break;
			pt[0] += alpha * v[0]/norm;
			pt[1] += alpha * v[1]/norm;
			alpha *= 0.99;
		}

		out.printf("%.14f\n", pt[0]);
		out.printf("%.14f\n", pt[1]);
	}
	
	void check(double[] pt, int[][][] ps, int[][] es)
	{
		int n = ps.length;
		int m = es.length;
		for(int z = 0;z < n;z++){
			int[][] poly = ps[z];
			for(int i = 0, j = 1;i < poly.length;i++,j++){
				if(j == poly.length)j = 0;
				
				if(Line2D.relativeCCW(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]) <= 0){
				}else if(Line2D.ptSegDist(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]) <= 3e-5){
				}else{
					throw new RuntimeException("poly " + z + " " + i + " "  +j + " " + Line2D.relativeCCW(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]) + " " + Line2D.ptSegDist(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]));
				}
			}
		}
		for(int z = 0;z < m;z++){
			int[] e = es[z];
			if(Point2D.distance(e[0], e[1], pt[0], pt[1]) + Point2D.distance(e[2], e[3], pt[0], pt[1]) <= 2*e[4] + 3e-5){
			}else{
				throw new RuntimeException("ell " + z);
			}
		}
		
	}
	
	void moveP(double[] pt, int[][] poly, double[] v)
	{
		int n = poly.length;
		for(int i = 0, j = 1;i < poly.length;i++,j++){
			if(j == n)j = 0;
			
			if(Line2D.relativeCCW(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]) > 0){
				double t = leg(poly[i][0], poly[i][1], poly[j][0], poly[j][1], pt[0], pt[1]);
				double lx = poly[i][0] + t * (poly[j][0] - poly[i][0]);
				double ly = poly[i][1] + t * (poly[j][1] - poly[i][1]);
				v[0] += lx - pt[0];
				v[1] += ly - pt[1];

			}
		}
	}
	
		void moveE(double[] pt, int[] e, double[] v, double[][][] trans)
	{
		if(Point2D.distance(e[0], e[1], pt[0], pt[1]) + Point2D.distance(e[2], e[3], pt[0], pt[1]) > 2*e[4]){
			double[] tpt = mul(trans[0], new double[]{pt[0], pt[1], 1});
			boolean xflip = false, yflip = false;
			if(tpt[0] < 0){
				tpt[0] = -tpt[0];
				xflip = true;
			}
			if(tpt[1] < 0){
				tpt[1] = -tpt[1];
				yflip = true;
			}
			double low = 0, high = Math.PI/2;
			double A = e[4];
			double r = Point2D.distance(e[0], e[1], e[2], e[3]);
			double B = Math.sqrt(e[4]*e[4]-(r/2)*(r/2));
			for(int rep = 0;rep < 30;rep++){
				double h = (low+high)/2;
				double x0 = A*Math.cos(h);
				double y0 = B*Math.sin(h);

				if(Line2D.relativeCCW(x0, y0, x0 + x0/A/A, y0 + y0/B/B, tpt[0], tpt[1]) < 0){
					low = h;
				}else{
					high = h;
				}
			}
			double x0 = A*Math.cos(low);
			double y0 = B*Math.sin(low);
			if(xflip)x0 = -x0;
			if(yflip)y0 = -y0;
			double[] pb = mul(trans[1], new double[]{x0, y0, 1});
			v[0] += pb[0] - pt[0];
			v[1] += pb[1] - pt[1];
		}
	}
	
	public static double leg(double ax, double ay, double bx, double by, double px, double py)
	{
		return ((px-ax)*(bx-ax)+(py-ay)*(by-ay)) / ((bx-ax)*(bx-ax)+(by-ay)*(by-ay));
	}
	
	public static double[] mul(double[][] A, double[] v)
	{
		int m = A.length;
		int n = v.length;
		double[] w = new double[m];
		for(int i = 0;i < m;i++){
			double sum = 0;
			for(int k = 0;k < n;k++){
				sum += A[i][k] * v[k];
			}
			w[i] = sum;
		}
		return w;
	}
	
	double[][][] trans(double ax, double ay, double bx, double by, double cx, double cy, double dx, double dy)
	{
		// scale
		double scale = Point2D.distance(cx, cy, dx, dy) / Point2D.distance(ax, ay, bx, by);
		ax *= scale; ay *= scale;
		bx *= scale; by *= scale;
		
		// rotation
		double theta = Math.atan2(dy-cy, dx-cx) - Math.atan2(by-ay, bx-ax);
		double cos = Math.cos(theta), sin = Math.sin(theta);
		double nax = ax * cos - ay * sin;
		double nay = ax * sin + ay * cos;
		
		// translate
		double tx = cx-nax;
		double ty = cy-nay;
		

		return new double[][][]{
			new double[][]{
				{cos*scale, -sin*scale, tx},
				{sin*scale, cos*scale, ty},
				{0, 0, 1}
			},
			new double[][]{
				{cos/scale, sin/scale, -cos/scale*tx-sin/scale*ty},
				{-sin/scale, cos/scale, sin/scale*tx-cos/scale*ty},
				{0, 0, 1}
			}
		};
	}

	
	void run() throws Exception
	{
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		
		long s = System.currentTimeMillis();
		solve();
		out.flush();
		if(!INPUT.isEmpty())tr(System.currentTimeMillis()-s+"ms");
	}
	
	public static void main(String[] args) throws Exception { new GoodPoint().run(); }
	
	private byte[] inbuf = new byte[1024];
	public int lenbuf = 0, ptrbuf = 0;
	
	private int readByte()
	{
		if(lenbuf == -1)throw new InputMismatchException();
		if(ptrbuf >= lenbuf){
			ptrbuf = 0;
			try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
			if(lenbuf <= 0)return -1;
		}
		return inbuf[ptrbuf++];
	}
	
	private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
	private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
	
	private double nd() { return Double.parseDouble(ns()); }
	private char nc() { return (char)skip(); }
	
	private String ns()
	{
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}
	
	private char[] ns(int n)
	{
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while(p < n && !(isSpaceChar(b))){
			buf[p++] = (char)b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}
	
	private char[][] nm(int n, int m)
	{
		char[][] map = new char[n][];
		for(int i = 0;i < n;i++)map[i] = ns(m);
		return map;
	}
	
	private int[] na(int n)
	{
		int[] a = new int[n];
		for(int i = 0;i < n;i++)a[i] = ni();
		return a;
	}
	
	private int ni()
	{
		int num = 0, b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private long nl()
	{
		long num = 0;
		int b;
		boolean minus = false;
		while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
		if(b == '-'){
			minus = true;
			b = readByte();
		}
		
		while(true){
			if(b >= '0' && b <= '9'){
				num = num * 10 + (b - '0');
			}else{
				return minus ? -num : num;
			}
			b = readByte();
		}
	}
	
	private static void tr(Object... o) { System.out.println(Arrays.deepToString(o)); }
}