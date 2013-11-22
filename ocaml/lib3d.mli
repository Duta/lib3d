type vec3
type mat3

val negate              : vec3  -> vec3
val add                 : vec3  -> vec3  -> vec3
val subtract            : vec3  -> vec3  -> vec3
val mul_vec_sf          : vec3  -> float -> vec3
val mul_vec_mat         : vec3  -> mat3  -> vec3
val mul_mat_mat         : mat3  -> mat3  -> mat3
val divide              : vec3  -> float -> vec3
val dotProduct          : vec3  -> vec3  -> float
val crossProduct        : vec3  -> vec3  -> vec3
val tripleProduct       : vec3  -> vec3  -> vec3  -> float
val magnitude           : vec3  -> float
val distance            : vec3  -> vec3  -> float
val normalize           : vec3  -> vec3
val xAxisRotationMatrix : float -> mat3
val yAxisRotationMatrix : float -> mat3
val zAxisRotationMatrix : float -> mat3
val axisRotationMatrix  : vec3  -> float -> mat3
val scale_sf            : float -> mat3
val scale_xyz           : float -> float -> float -> mat3
val scale_axis          : vec3  -> float -> mat3
val orthoProjection     : vec3  -> mat3
val reflection          : vec3  -> mat3
