#Data types
These are all structs:

##Vector3, Point3
Vector3 and Point3 are equivalent - it's just a typedef to help make code clear.

They have 3 fields - **x**, **y** and **z**.

##Vector4
Vector4 is the same as Vector3 but with an additional field - **w**.

##Matrix3
Matrix3 represents a 3x3 square matrix.

Its fields are:

**m11**, **m12**, **m13**,

**m21**, **m22**, **m23**,

**m31**, **m32**, **m33**.

##Matrix4
Matrix4 represents a 4x4 square matrix.

Its fields are:

**m11**, **m12**, **m13**, **m14**,

**m21**, **m22**, **m23**, **m24**,

**m31**, **m32**, **m33**, **m34**,

**m41**, **m42**, **m43**, **m44**.

#Method summary

##negate(Vector3)
Negates the vector.

Given a vector [a b c], returns [-a -b -c].

##add(Vector3, Vector3)
Adds the two vectors.

Given [a b c] and [d e f], returns [a+d b+e c+f].

##subtract(Vector3, Vector3)
Subtracts the second vector from the first.

Given [a b c] and [d e f], returns [a-d b-e c-f].

##multiply(Vector3, double)
Multiplys the vector by the scalar.

Given [a b c] and 2.0, returns [2a 2b 2c].

##multiply(Vector3, Matrix3)
Multiplys the vector by the matrix.

Returns [vx*m11+vy*m21+vz*m31 vx*m12+vy*m22+vz*m32 vx*m13+vy*m23+vz*m33]

Bear in mind here that lib3d uses *row* vectors.

##multiply(Matrix3, Matrix3)
Given matrices **A** and **B**, returns **AB**.

##divide(Vector3, double)
Divides the vector by the scalar.

Given [a b c] and 2.0, returns [a/2 b/2 c/2].

If the scalar given is 0, the zero vector is returned.

##dotProduct(Vector3, Vector3)
Returns the dot product of the two vectors.

##crossProduct(Vector3, Vector3)
Returns the cross product of the two vectors.

##tripleProduct(Vector3, Vector3, Vector3)
Returns the triple product of the three vectors.

Given vectors **a**, **b** and **c**, returns dotProduct(a, crossProduct(b, c)).

##magnitude(Vector3)
Returns the magnitude of the vector.

##distance(Vector3, Vector3)
Returns the distance between the two vectors.

Given vectors **a** and **b**, returns magnitude(subtract(a, b)).

##normalize(Vector3)
Normalizes the vector.

Given a vector **v**, returns divide(v, magnitude(v)).

Given the zero vector, the zero vector is returned.

##xAxisRotationMatrix(double)
Given an angle theta (in radians), returns a matrix that can be used for rotating theta degrees about the x axis.

##yAxisRotationMatrix(double)
Given an angle theta (in radians), returns a matrix for rotating theta degrees about the x axis.

##zAxisRotationMatrix(double)
Given an angle theta (in radians), returns a matrix that can be used for rotating theta degrees about the x axis.

##axisRotationMatrix(Vector3, double)
Given an angle theta (in radians), returns a matrix that can be used for rotating theta degrees about the given axis.

##scale(double)
Returns a matrix representing a uniform scale by the given scale factor.

##scale(double, double, double)
The three parameters are scale factors in the x, y & z directions respectively.

Returns a matrix representing a scale by the given scale factors.

##scale(Vector3, double)
Returns a matrix representing a scale by the given scale factor in the direction of the given vector.

##orthoProject(Vector3)
Returns a matrix for orthographically projecting a 3D objects onto a 2D plane. The given vector is perpendicular to the plane.

##reflection(Vector3)
Returns a matrix for reflecting through a plane. The given vector is perpendicular to the plane.
