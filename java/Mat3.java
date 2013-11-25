public class Mat3 {
    private double[][] data;

    public Mat3(double m11, double m12, double m13,
                double m21, double m22, double m23,
                double m31, double m32, double m33) {
        if(anyNaN(m11, m12, m13,
                  m21, m22, m23,
                  m31, m32, m33)) {
            throw new IllegalArgumentException(
                "Can't use NaN values in a matrix");
        }
        if(anyInfinite(m11, m12, m13,
                       m21, m22, m23,
                       m31, m32, m33)) {
            throw new IllegalArgumentException(
                "Can't use infinite values in a matrix");
        }
        data = new double[][] {
            new double[] {m11, m12, m13},
            new double[] {m21, m22, m23},
            new double[] {m31, m32, m33}
        };
    }

    public static Mat3 xAxisRotationMatrix(double theta) {
        if(Double.isNaN(theta)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for theta");
        }
        if(Double.isInfinite(theta)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for theta");
        }
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
            1, 0, 0,
            0, c, s,
            0, s, c);
    }

    public static Mat3 yAxisRotationMatrix(double theta) {
        if(Double.isNaN(theta)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for theta");
        }
        if(Double.isInfinite(theta)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for theta");
        }
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
            c, 0, s,
            0, 1, 0,
            s, 0, c);
    }

    public static Mat3 zAxisRotationMatrix(double theta) {
        if(Double.isNaN(theta)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for theta");
        }
        if(Double.isInfinite(theta)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for theta");
        }
        double s = Math.sin(theta);
        double c = Math.cos(theta);
        return new Mat3(
             c, s, 0,
            -s, c, 0,
             0, 0, 1);
    }

    public static Mat3 axisRotationMatrix(Vec3 axis, double theta) {
        if(axis == null) {
            throw new IllegalArgumentException(
                "Can't use a null axis vector");
        }
        if(Double.isNaN(theta)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for theta");
        }
        if(Double.isInfinite(theta)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for theta");
        }
        Vec3 v = axis.normalize();
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
        if(Double.isNaN(sf)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for the scale factor");
        }
        if(Double.isInfinite(sf)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for the scale factor");
        }
        return Mat3.scale(sf, sf, sf);
    }

    public static Mat3 scale(double sfx, double sfy, double sfz) {
        if(anyNaN(sfx, sfy, sfz)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for the scale factors");
        }
        if(anyInfinite(sfx, sfy, sfz)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for the scale factors");
        }
        return new Mat3(
            sfx, 0, 0,
            0, sfy, 0,
            0, 0, sfz);
    }

    public static Mat3 scale(Vec3 axis, double sf) {
        if(axis == null) {
            throw new IllegalArgumentException(
                "Can't use a null axis vector");
        }
        if(Double.isNaN(sf)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for the scale factor");
        }
        if(Double.isInfinite(sf)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for the scale factor");
        }
        return new Mat3(
            sf*axis.getX()*axis.getX() + 1,
            sf*axis.getX()*axis.getY(),
            sf*axis.getX()*axis.getZ(),

            sf*axis.getY()*axis.getX(),
            sf*axis.getY()*axis.getY() + 1,
            sf*axis.getY()*axis.getZ(),

            sf*axis.getZ()*axis.getX(),
            sf*axis.getZ()*axis.getY(),
            sf*axis.getZ()*axis.getZ() + 1);
    }

    public static Mat3 orthoProjection(Vec3 axis) {
        if(axis == null) {
            throw new IllegalArgumentException(
                "Can't use a null axis vector");
        }
        return Mat3.scale(axis, 0);
    }

    public static Mat3 reflection(Vec3 axis) {
        if(axis == null) {
            throw new IllegalArgumentException(
                "Can't use a null axis vector");
        }
        return Mat3.scale(axis, -1);
    }

    public Vec3 getRow(int i) {
        try {
            return new Vec3(
                get(i, 1), get(i, 2), get(i, 3));
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "i="+i+" isn't a valid row index");
        }
    }

    public Vec3 getCol(int i) {
        try {
            return new Vec3(
                get(1, i), get(2, i), get(3, i));
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "i="+i+" isn't a valid column index");
        }
    }

    public double get(int i, int j) {
        try {
            return data[i-1][j-1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                "i,j="+i+","+j+" isn't a valid matrix index");
        }
    }

    public Mat3 multiply(Mat3 m) {
        if(m == null) {
            throw new IllegalArgumentException(
                "Can't multiply a matrix by null");
        }
        Vec3 p = getRow(1).multiply(m);
        Vec3 q = getRow(2).multiply(m);
        Vec3 r = getRow(3).multiply(m);
        return new Mat3(
            p.getX(), p.getY(), p.getZ(),
            q.getX(), q.getY(), q.getZ(),
            r.getX(), r.getY(), r.getZ());
    }

    private static final boolean anyNaN(double... values) {
        for(double value : values) {
            if(Double.isNaN(value)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean anyInfinite(double... values) {
        for(double value : values) {
            if(Double.isInfinite(value)) {
                return true;
            }
        }
        return false;
    }
}