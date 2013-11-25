public class Vec3 {
    private double x, y, z;

    public Vec3(double x, double y, double z) {
        if(Double.isNaN(x)
        || Double.isNaN(y)
        || Double.isNaN(z)) {
            throw new IllegalArgumentException(
                "Can't use NaN values in a vector");
        }
        if(Double.isInfinite(x)
        || Double.isInfinite(y)
        || Double.isInfinite(z)) {
            throw new IllegalArgumentException(
                "Can't use infinite values in a vector");
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Object anObject) {
        if(anObject == null
        || !(anObject instanceof Vec3)) {
            return false;
        }
        Vec3 v = (Vec3)anObject;
        return x == v.x
            && y == v.y
            && z == v.z;
    }

    public boolean equals(Vec3 v, double epsilon) {
        if(Double.isNaN(epsilon)) {
            throw new IllegalArgumentException(
                "Can't use NaN values for epsilon");
        }
        if(Double.isInfinite(epsilon)) {
            throw new IllegalArgumentException(
                "Can't use infinite values for epsilon");
        }
        if(v == null) {
            return false;
        }
        return Math.abs(x - v.x) <= epsilon
            && Math.abs(y - v.y) <= epsilon
            && Math.abs(z - v.z) <= epsilon;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vec3 negate() {
        return new Vec3(-x, -y, -z);
    }

    public Vec3 add(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't add null to a vector");
        }
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    public Vec3 subtract(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't subtract null from a vector");
        }
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    public Vec3 multiply(double k) {
        if(Double.isNaN(k)) {
            throw new IllegalArgumentException(
                "Can't multiply a vector by NaN values");
        }
        if(Double.isInfinite(k)) {
            throw new IllegalArgumentException(
                "Can't multiply a vector by infinite values");
        }
        return new Vec3(x * k, y * k, z * k);
    }

    public Vec3 multiply(Mat3 m) {
        if(m == null) {
            throw new NullPointerException(
                "Can't multiply a vector by null");
        }
        return new Vec3(
            dotProduct(m.getCol(1)),
            dotProduct(m.getCol(2)),
            dotProduct(m.getCol(3)));
    }

    public Vec3 divide(double k) {
        if(k == 0.0) {
            throw new ArithmeticException(
                "Can't divide a vector by zero");
        }
        if(Double.isNaN(k)) {
            throw new IllegalArgumentException(
                "Can't divide a vector by NaN values");
        }
        if(Double.isInfinite(k)) {
            throw new IllegalArgumentException(
                "Can't divide a vector by infinite values");
        }
        return new Vec3(x / k, y / k, z / k);
    }

    public double dotProduct(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't dot product a vector with null");
        }
        return x*v.x + y*v.y + z*v.z;
    }

    public Vec3 crossProduct(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't cross product a vector with null");
        }
        return new Vec3(
            y*v.z - z*v.y,
            z*v.x - x*v.z,
            x*v.y - y*v.x);
    }

    public double tripleProduct(Vec3 v1, Vec3 v2) {
        if(v1 == null || v2 == null) {
            throw new NullPointerException(
                "Can't triple product a vector with null");
        }
        return dotProduct(v1.crossProduct(v2));
    }

    public double magnitude() {
        return Math.sqrt(dotProduct(this));
    }

    public double distance(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't calculate the distance between a vector and null");
        }
        return subtract(v).magnitude();
    }

    public Vec3 normalize() {
        return divide(magnitude());
    }
}