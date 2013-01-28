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

    Vector3 negate              (Vector3);
    Vector3 add                 (Vector3, Vector3);
    Vector3 subtract            (Vector3, Vector3);
    Vector3 multiply            (Vector3, double);
    Vector3 multiply            (Vector3, Matrix3);
    Matrix3 multiply            (Matrix3, Matrix3);
    Vector3 divide              (Vector3, double);
    double  dotProduct          (Vector3, Vector3);
    Vector3 crossProduct        (Vector3, Vector3);
    double  tripleProduct       (Vector3, Vector3, Vector3);
    double  magnitude           (Vector3);
    double  distance            (Vector3, Vector3);
    Vector3 normalize           (Vector3);
    Matrix3 xAxisRotationMatrix (double);
    Matrix3 yAxisRotationMatrix (double);
    Matrix3 zAxisRotationMatrix (double);
    Matrix3 axisRotationMatrix  (Vector3, double);
    Matrix3 scale               (double);
    Matrix3 scale               (double, double, double);
    Matrix3 scale               (Vector3, double);
    Matrix3 orthoProjection     (Vector3);
    Matrix3 reflection          (Vector3);
}
