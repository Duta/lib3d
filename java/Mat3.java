public class Mat3 {
    private double[][] data;

    public Mat3(double m11, double m12, double m13,
                double m21, double m22, double m23,
                double m31, double m32, double m33) {
        data = new double[][] {
            new double[] {m11, m12, m13},
            new double[] {m21, m22, m23},
            new double[] {m31, m32, m33}
        };
    }

    public static Mat3 xAxisRotationMatrix(double theta) {
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
            1, 0, 0,
            0, c, s,
            0, s, c);
    }

    public static Mat3 yAxisRotationMatrix(double theta) {
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
            c, 0, s,
            0, 1, 0,
            s, 0, c);
    }

    public static Mat3 zAxisRotationMatrix(double theta) {
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
             c, s, 0,
            -s, c, 0,
             0, 0, 1);
    }

    public static Mat3 axisRotationMatrix(Vec3 v, double theta) {
        v = v.normalize();
        double sin = Math.sin(theta);
        double cos = Math.cos(theta);
        double negCos = 1 - cos;
        return new Mat3(
            v.getX()*v.getX()*negCos + cos,
            v.getX()*v.getY()*negCos + sin*v.getZ(),
            v.getX()*v.getZ()*negCos - sin*v.getY(),

            v.getX()*v.getY()*negCos - sin*v.getZ(),
            v.getY()*v.getY()*negCos + cos,
            v.getY()*v.getZ()*negCos + sin*v.getX(),

            v.getX()*v.getZ()*negCos + sin*v.getY(),
            v.getY()*v.getZ()*negCos - sin*v.getX(),
            v.getZ()*v.getZ()*negCos + cos);
    }

    public static Mat3 scale(double sf) {
        return Mat3.scale(sf, sf, sf);
    }

    public static Mat3 scale(double sfx, double sfy, double sfz) {
        return new Mat3(
            sfx, 0, 0,
            0, sfy, 0,
            0, 0, sfz);
    }

    public static Mat3 scale(Vec3 v, double sf) {
        return new Mat3(
            sf*v.getX()*v.getX() + 1,
            sf*v.getX()*v.getY(),
            sf*v.getX()*v.getZ(),

            sf*v.getY()*v.getX(),
            sf*v.getY()*v.getY() + 1,
            sf*v.getY()*v.getZ(),

            sf*v.getZ()*v.getX(),
            sf*v.getZ()*v.getY(),
            sf*v.getZ()*v.getZ() + 1);
    }

    public static Mat3 orthoProjection(Vec3 v) {
        return Mat3.scale(v, 0);
    }

    public static Mat3 reflection(Vec3 v) {
        return Mat3.scale(v, -1);
    }

    public Vec3 getRow(int i) {
        try {
            return new Vec3(
                get(i, 1), get(i, 2), get(i, 3));
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "i="+i+" is not a valid row index");
        }
    }

    public Vec3 getCol(int i) {
        try {
            return new Vec3(
                get(1, i), get(2, i), get(3, i));
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "i="+i+" is not a valid column index");
        }
    }

    public double get(int i, int j) {
        try {
            return data[i-1][j-1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "i,j="+i+","+j+" is not a valid matrix index");
        }
    }

    public Mat3 multiply(Mat3 m) {
        Vec3 p = getRow(1).multiply(m);
        Vec3 q = getRow(2).multiply(m);
        Vec3 r = getRow(3).multiply(m);
        return new Mat3(
            p.getX(), p.getY(), p.getZ(),
            q.getX(), q.getY(), q.getZ(),
            r.getX(), r.getY(), r.getZ());
    }
}