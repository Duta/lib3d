#include <cmath>

namespace lib3d
{
    typedef struct Double3
    {
        double x;
        double y;
        double z;
    } Vector3, Point3;

    struct Matrix3
    {
        double
            m11, m12, m13,
            m21, m22, m23,
            m31, m32, m33;
    };

    Vector3 negate        (Vector3);
    Vector3 add           (Vector3, Vector3);
    Vector3 subtract      (Vector3, Vector3);
    Vector3 multiply      (Vector3, double);
    Vector3 multiply      (Vector3, Matrix3);
    Vector3 divide        (Vector3, double);
    double  dotProduct    (Vector3, Vector3);
    Vector3 crossProduct  (Vector3, Vector3);
    double  tripleProduct (Vector3, Vector3, Vector3);
    double  magnitude     (Vector3);
    double  distance      (Vector3, Vector3);
    Vector3 normalize     (Vector3);
}
