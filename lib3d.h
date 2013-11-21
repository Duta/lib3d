#include <cmath>

namespace lib3d
{
    typedef struct Double3
    {
        double x;
        double y;
        double z;
    } Vector3, Point3;

    struct Vector4
    {
        double x;
        double y;
        double z;
        double w;
    };

    struct Matrix3
    {
        double
            m11, m12, m13,
            m21, m22, m23,
            m31, m32, m33;
    };

    struct Matrix4
    {
        double
            m11, m12, m13, m14,
            m21, m22, m23, m24,
            m31, m32, m33, m34,
            m41, m42, m43, m44;
    };

    Vector3 negate              (const Vector3);
    Vector3 add                 (const Vector3, const Vector3);
    Vector3 subtract            (const Vector3, const Vector3);
    Vector3 multiply            (const Vector3, const double);
    Vector3 multiply            (const Vector3, const Matrix3);
    Matrix3 multiply            (const Matrix3, const Matrix3);
    Vector3 divide              (const Vector3, const double);
    double  dotProduct          (const Vector3, const Vector3);
    Vector3 crossProduct        (const Vector3, const Vector3);
    double  tripleProduct       (const Vector3, const Vector3, const Vector3);
    double  magnitude           (const Vector3);
    double  distance            (const Vector3, const Vector3);
    Vector3 normalize           (const Vector3);
    Matrix3 xAxisRotationMatrix (const double);
    Matrix3 yAxisRotationMatrix (const double);
    Matrix3 zAxisRotationMatrix (const double);
    Matrix3 axisRotationMatrix  (const Vector3, const double);
    Matrix3 scale               (const double);
    Matrix3 scale               (const double,  const double,  const double);
    Matrix3 scale               (const Vector3, const double);
    Matrix3 orthoProjection     (const Vector3);
    Matrix3 reflection          (const Vector3);
}
