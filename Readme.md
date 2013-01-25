#lib3d Documentation

##Info

This is a library entirely written by me.

Because of that, and the sheer number of better libraries out there, I say this:

**I don't encourage use of this library.**

I know that's a weird thing for its creator to say, but it's true (for now, at least). Use a well-established library (there are many).

This library is mainly a self-teaching exercise. If you want to look over the code, be my guest! I'm also open to pull requests, if you have any.

##Notes

 - Uses row vectors (not column ones).
 - Uses a left-handed co-ordinate system.

##Method summary

###negate(Vector3)
Negates the vector.

Given a vector [a b c], returns [-a -b -c].

###add(Vector3, Vector3)
Adds the two vectors.

Given [a b c] and [d e f], returns [a+d b+e c+f].

###subtract(Vector3, Vector3)
Subtracts the second vector from the first.

Given [a b c] and [d e f], returns [a-d b-e c-f].

###multiply(Vector3, double)
Multiplys the vector by the scalar.

Given [a b c] and 2.0, returns [2a 2b 2c].

###multiply(Vector3, Matrix3)
Multiplys the vector by the matrix.

Returns [vx*m11+vy*m21+vz*m31 vx*m12+vy*m22+vz*m32 vx*m13+vy*m23+vz*m33]

Bear in mind here that lib3d uses *row* vectors.

###divide(Vector3, double)
Divides the vector by the scalar.

Given [a b c] and 2.0, returns [a/2 b/2 c/2].

If the scalar given is 0, the zero vector is returned.

###dotProduct(Vector3, Vector3)
Returns the dot product of the two vectors.

###crossProduct(Vector3, Vector3)
Returns the cross product of the two vectors.

###tripleProduct(Vector3, Vector3, Vector3)
Returns the triple product of the three vectors.

Given vectors **a**, **b** and **c**, returns dotProduct(a, crossProduct(b, c)).

###magnitude(Vector3)
Returns the magnitude of the vector.

###distance(Vector3, Vector3)
Returns the distance between the two vectors.

Given vectors **a** and **b**, returns magnitude(subtract(a, b)).

###normalize(Vector3)
Normalizes the vector.

Given a vector **v**, returns divide(v, magnitude(v)).

Given the zero vector, the zero vector is returned.
