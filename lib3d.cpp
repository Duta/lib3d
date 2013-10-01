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

Matrix3 lib3d::multiply(Matrix3 A, Matrix3 B)
{
    /* Can be sped up */

    Vector3 p;
    p.x = A.m11;
    p.y = A.m12;
    p.z = A.m13;
    Vector3 q;
    q.x = A.m21;
    q.y = A.m22;
    q.z = A.m23;
    Vector3 r;
    r.x = A.m31;
    r.y = A.m32;
    r.z = A.m33;

    p = multiply(p, B);
    q = multiply(q, B);
    r = multiply(r, B);

    Matrix3 M;
    M.m11 = p.x;
    M.m12 = p.y;
    M.m13 = p.z;
    M.m21 = q.x;
    M.m22 = q.y;
    M.m23 = q.z;
    M.m31 = r.x;
    M.m32 = r.y;
    M.m33 = r.z;
    return M;
}

Vector3 lib3d::divide(Vector3 v, double k)
{
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

Matrix3 lib3d::xAxisRotationMatrix(double theta)
{
    double sinTheta = sin(theta);
    double cosTheta = cos(theta);

    Matrix3 M;
    M.m11 = 1;
    M.m12 = 0;
    M.m13 = 0;
    M.m21 = 0;
    M.m22 = cosTheta;
    M.m23 = sinTheta;
    M.m31 = 0;
    M.m32 = sinTheta;
    M.m33 = cosTheta;
    return M;
}

Matrix3 lib3d::yAxisRotationMatrix(double theta)
{
    double sinTheta = sin(theta);
    double cosTheta = cos(theta);

    Matrix3 M;
    M.m11 = cosTheta;
    M.m12 = 0;
    M.m13 = sinTheta;
    M.m21 = 0;
    M.m22 = 1;
    M.m23 = 0;
    M.m31 = sinTheta;
    M.m32 = 0;
    M.m33 = cosTheta;
    return M;
}

Matrix3 lib3d::zAxisRotationMatrix(double theta)
{
    double sinTheta = sin(theta);
    double cosTheta = cos(theta);

    Matrix3 M;
    M.m11 = cosTheta;
    M.m12 = sinTheta;
    M.m13 = 0;
    M.m21 = -sinTheta;
    M.m22 = cosTheta;
    M.m23 = 0;
    M.m31 = 0;
    M.m32 = 0;
    M.m33 = 1;
    return M;
}

Matrix3 lib3d::axisRotationMatrix(Vector3 v, double theta)
{
    /* Just in case */
    v = normalize(v);

    double sinTheta = sin(theta);
    double cosTheta = cos(theta);
    double negCos = 1 - cosTheta;

    Matrix3 M;
    M.m11 = v.x * v.x * negCos + cosTheta;
    M.m12 = v.x * v.y * negCos + v.z * sinTheta;
    M.m13 = v.x * v.z * negCos - v.y * sinTheta;
    M.m21 = v.x * v.y * negCos - v.z * sinTheta;
    M.m22 = v.y * v.y * negCos + cosTheta;
    M.m23 = v.y * v.z * negCos + v.x * sinTheta;
    M.m31 = v.x * v.z * negCos + v.y * sinTheta;
    M.m32 = v.y * v.z * negCos - v.x * sinTheta;
    M.m33 = v.z * v.z * negCos + cosTheta;
    return M;
}

Matrix3 lib3d::scale(double sf)
{
    Matrix3 M;
    M.m11 = M.m22 = M.m33 = sf;
    M.m12 = M.m13 = M.m21 = M.m23 = M.m31 = M.m32 = 0;
    return M;
}

Matrix3 lib3d::scale(double sfx, double sfy, double sfz)
{
    Matrix3 M;
    M.m11 = sfx;
    M.m22 = sfy;
    M.m33 = sfz;
    M.m12 = M.m13 = M.m21 = M.m23 = M.m31 = M.m32 = 0;
    return M;
}

Matrix3 lib3d::scale(Vector3 v, double sf)
{
    /* Just in case */
    v = normalize(v);

    sf = sf - 1;

    Matrix3 M;
    M.m11 = 1 + sf * v.x * v.x;
    M.m12 = sf * v.x * v.y;
    M.m13 = sf * v.x * v.z;
    M.m21 = sf * v.x * v.y;
    M.m22 = 1 + sf * v.y * v.y;
    M.m23 = sf * v.y * v.z;
    M.m31 = sf * v.x * v.z;
    M.m32 = sf * v.y * v.z;
    M.m33 = 1 + sf * v.z * v.z;
    return M;
}

Matrix3 lib3d::orthoProjection(Vector3 v)
{
    /* Can be sped up */
    return scale(v, 0);
}

Matrix3 lib3d::reflection(Vector3 v)
{
    /* Can be sped up */
    return scale(v, -1);
}
