#include <cmath>
#include "lib3d.h"

using namespace lib3d;

Vector3 lib3d::negate(Vector3 v)
{
	Vector3 v2;
	v2.x = -v.x;
	v2.y = -v.y;
	v2.z = -v.z;
    return v2;
}

Vector3 lib3d::add(Vector3 a, Vector3 b)
{
	Vector3 v;
	v.x = a.x + b.x;
	v.y = a.y + b.y;
	v.z = a.z + b.z;
	return v;
}

Vector3 lib3d::subtract(Vector3 a, Vector3 b)
{
	//return add(a, negate(b));
	Vector3 v;
	v.x = a.x - b.x;
	v.y = a.y - b.y;
	v.z = a.z - b.z;
	return v;
}

Vector3 lib3d::multiply(Vector3 v, double k)
{
    Vector3 v2;
    v2.x = v.x * k;
    v2.y = v.y * k;
    v2.z = v.z * k;
    return v2;
}

Vector3 lib3d::multiply(Vector3 v, Matrix3 M)
{
    Vector3 v2;
    v2.x = v.x * M.m11 + v.y * M.m21 + v.z * M.m31;
    v2.y = v.x * M.m12 + v.y * M.m22 + v.z * M.m32;
    v2.z = v.x * M.m13 + v.y * M.m23 + v.z * M.m33;
    return v2;
}

Vector3 lib3d::divide(Vector3 v, double k)
{
    // Is this a good thing
    // to be doing? Not sure
    // what should be done
    // about possible divide-
    // by-zero errors...
    if(k == 0)
    {
        return Vector3{0, 0, 0};
    }

    Vector3 v2;
    v2.x = v.x / k;
    v2.y = v.y / k;
    v2.z = v.z / k;
    return v2;
}

double lib3d::dotProduct(Vector3 a, Vector3 b)
{
    return a.x * b.x
         + a.y * b.y
         + a.z * b.z;
}

Vector3 lib3d::crossProduct(Vector3 a, Vector3 b)
{
    Vector3 v;
    v.x = a.y * b.z - a.z * b.y;
    v.y = a.z * b.x - a.x * b.z;
    v.z = a.x * b.y - a.y * b.x;
    return v;
}

double lib3d::tripleProduct(Vector3 a, Vector3 b, Vector3 c)
{
    return dotProduct(a, crossProduct(b, c));
}

double lib3d::magnitude(Vector3 v)
{
    //return sqrt(dotProduct(v, v));
    return sqrt(v.x * v.x
              + v.y * v.y
              + v.z * v.z);
}

double lib3d::distance(Vector3 a, Vector3 b)
{
	return magnitude(subtract(a, b));
}

Vector3 lib3d::normalize(Vector3 v)
{
    return divide(v, magnitude(v));
}
