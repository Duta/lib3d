/**
 * The <code>Vec3</code> class is used for
 * representing immutable vectors in
 * three-dimensional cartesian space.
 *
 * Note that in the method comments, they imply
 * mutability, but this is simply for clarity
 * of language. For example, upon seeing:
 *
 * Negates the vector.
 *
 * Read:
 *
 * Returns the negated version of this vector.
 */
public class Vec3 {
    private double x, y, z;

    /**
     * Creates a vector with the given
     * x, y and z components.
     *
     * @param x The x component
     * @param y The y component
     * @param z The z component
     *
     * @throws IllegalArgumentException
     *  if any of x, y, or z were NaN
     * @throws IllegalArgumentException
     *  if any of x, y, or z were infinite
     */
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

    /**
     * Compares the vector to the specified object.
     * The result is true iff the argument is non-null
     * and is a <code>Vec3</code> object that has
     * exactly the same x, y and z values.
     *
     * You may wish to provide some epsilon, since the
     * components are stored as floating-point values.
     * To do this, use the other
     * {@link #equals(Vec3, double) equals}
     * method.
     *
     * @param anObject The object to compare against
     *
     * @return <code>true</code> if the given object
     *         represents a Vec3 with exactly the
     *         same x, y and z components as this
     *         vector, false otherwise.
     * @see #equals(Vec3, double)
     */
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

    /**
     * Returns <code>true</code> iff v is non-null
     * and for all of the different components
     * (x/y/z), the absolute difference between
     * that component in this vector and the same
     * component in v is less than or equal to
     * <code>epsilon</code>.
     *
     * @param v The vector to compare against
     * @param epsilon The epsilon to use when comparing
     *                the vectors' components
     *
     * @return <code>true</code> if all of the
     *         vector's components have an
     *         absolute difference of at most
     *         epsilon between them and their
     *         counterpart in v
     *
     * @throws IllegalArgumentException
     *  if epsilon was NaN
     * @throws IllegalArgumentException
     *  if epsilon was infinite
     *
     * @see #equals(Object)
     */
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

    /**
     * Returns the x component of the vector.
     *
     * @return the x component
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y component of the vector.
     *
     * @return the y component
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the z component of the vector.
     *
     * @return the z component
     */
    public double getZ() {
        return z;
    }

    /**
     * Negates the vector.
     *
     * Given [a b c], returns [-a -b -c].
     *
     * @return the vector, negated
     */
    public Vec3 negate() {
        return new Vec3(-x, -y, -z);
    }

    /**
     * Adds the vector to v.
     *
     * Given [a b c] and [d e f],
     * returns [a+d b+e c+f]
     *
     * @param v The vector to add
     *
     * @return the vector added to v
     *
     * @throws NullPointerException
     *  if v was null
     */
    public Vec3 add(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't add null to a vector");
        }
        return new Vec3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts <code>v</code>
     * from the vector.
     *
     * Given [a b c] and [d e f],
     * returns [a-d b-e c-f].
     *
     * @param v The vector to subtract
     *
     * @return the vector with
     *         <code>v</code> subtracted
     *
     * @throws NullPointerException
     *  if <code>v</code> was null
     */
    public Vec3 subtract(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't subtract null from a vector");
        }
        return new Vec3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Multiplies the vector
     * by <code>k</code>.
     *
     * Given [a b c] and 2.0,
     * returns [2a 2b 2c].
     *
     * @param k The value to multiply each
     *          component by
     *
     * @return the vector multiplied
     *         by <code>k</code>
     *
     * @throws IllegalArgumentException
     *  if k was NaN
     * @throws IllegalArgumentException
     *  if k was infinite
     */
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

    /**
     * Multiplies the vector by
     * <code>m</code>.
     *
     * Returns:
     *
     * [x*m11+y*m21+z*m31
     *  x*m12+y*m22+z*m32
     *  x*m13+y*m23+z*m33]
     *
     * Bear in mind here that lib3d
     * uses row vectors.
     *
     * @param m The matrix to multiply by
     *
     * @return the vector multiplied
     *         by <code>m</code>
     *
     * @throws NullPointerException
     *  if m was null
     */
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

    /**
     * Divides the vector by
     * <code>k</code>.
     *
     * Given [a b c] and 2.0,
     * returns [a/2 b/2 c/2].
     *
     * @param k The value to divide
     *          each component by
     *
     * @return the vector divided
     *         by <code>k</code>
     *
     * @throws ArithmeticException
     *  if k was 0.0
     * @throws IllegalArgumentException
     *  if k was NaN
     * @throws IllegalArgumentException
     *  if k was infinite
     */
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

    /**
     * Returns the dot product of the
     * vector and <code>v</code>.
     *
     * @param v The vector to dot
     *          product against
     *
     * @return the dot product of the
     *         vector and <code>v</code>
     *
     * @throws NullPointerException
     *  if v was null
     */
    public double dotProduct(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't dot product a vector with null");
        }
        return x*v.x + y*v.y + z*v.z;
    }

    /**
     * Returns the cross product of the
     * vector and <code>v</code>.
     *
     * @param v The vector to cross
     *          product against
     *
     * @return the cross product of the
     *         vector and <code>v</code>
     *
     * @throws NullPointerException
     *  if v was null
     */
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

    /**
     * Returns the triple product of the vector
     * <code>v1</code> and <code>v2</code>.
     *
     * Equivalent to:
     *
     * <code>dotProduct(v1.crossProduct(v2))</code>
     *
     * @param v1 The first vector to triple
     *           product against
     *
     * @param v2 The second vector to triple
     *           product against
     *
     * @return the triple product of the vector,
     *         <code>v1</code> and <code>v2</code>
     *
     * @throws NullPointerException
     *  if either <code>v1</code> or <code>v2</code>
     *  were null
     */
    public double tripleProduct(Vec3 v1, Vec3 v2) {
        if(v1 == null || v2 == null) {
            throw new NullPointerException(
                "Can't triple product a vector with null");
        }
        return dotProduct(v1.crossProduct(v2));
    }

    /**
     * Returns the magnitude of the vector.
     *
     * @return the magnitude of the vector
     */
    public double magnitude() {
        return Math.sqrt(dotProduct(this));
    }

    /**
     * Returns the distance between the
     * vector and <code>v</code>.
     *
     * Equivalent to:
     *
     * <code>subtract(v).magnitude()</code>
     *
     * @param v The vector to subtract
     *
     * @return the distance between the
     *         vector and <code>v</code>
     *
     * @throws NullPointerException
     *  if v was null
     */
    public double distance(Vec3 v) {
        if(v == null) {
            throw new NullPointerException(
                "Can't calculate the distance between a vector and null");
        }
        return subtract(v).magnitude();
    }

    /**
     * Normalizes the vector.
     *
     * Equivalent to:
     *
     * <code>divide(magnitude())</code>
     *
     * @return the vector, normalized
     */
    public Vec3 normalize() {
        return divide(magnitude());
    }
}