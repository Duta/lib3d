public class Vec3 {
    private double x;
    private double y;
    private double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vec3 v) {
        if(v == null) {
            return false;
        }
        return x == v.x
            && y == v.y
            && z == v.z;
    }

    public boolean equals(Vec3 v, double epsilon) {
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
        return new Vec3(
            -x, -y, -z);
    }

    public Vec3 add(Vec3 v) {
        return new Vec3(
            x + v.x, y + v.y, z + v.z);
    }

    public Vec3 subtract(Vec3 v) {
        return new Vec3(
            x - v.x, y - v.y, z - v.z);
    }

    public Vec3 multiply(double k) {
        return new Vec3(
            x * k, y * k, z * k);
    }

    public Vec3 multiply(Mat3 m) {
        return new Vec3(
            dotProduct(m.getCol(1)),
            dotProduct(m.getCol(2)),
            dotProduct(m.getCol(3)));
    }

    public Vec3 divide(double k) {
        return new Vec3(
            x / k, y / k, z / k);
    }

    public double dotProduct(Vec3 v) {
        return x*v.x
             + y*v.y
             + z*v.z;
    }

    public Vec3 crossProduct(Vec3 v) {
        return new Vec3(
            y*v.z - z*v.y,
            z*v.x - x*v.z,
            x*v.y - y*v.x);
    }

    public double tripleProduct(Vec3 v1, Vec3 v2) {
        return dotProduct(v1.crossProduct(v2));
    }

    public double magnitude() {
        return Math.sqrt(dotProduct(this));
    }

    public double distance(Vec3 v) {
        return subtract(v).magnitude();
    }

    public Vec3 normalize() {
        return divide(magnitude());
    }
}