type vec3 = {x : float; y : float; z : float};;
type mat3 = {
  m11 : float; m12 : float; m13 : float;
  m21 : float; m22 : float; m23 : float;
  m31 : float; m32 : float; m33 : float;
};;

let negate v = {
  x = -.v.x;
  y = -.v.y;
  z = -.v.z;
};;

let add a b = {
  x = a.x +. b.x;
  y = a.y +. b.y;
  z = a.z +. b.z;
};;

let subtract a b = {
  x = a.x -. b.x;
  y = a.y -. b.y;
  z = a.z -. b.z;
};;

let mul_vec_sf v sf = {
  x = v.x *. sf;
  y = v.y *. sf;
  z = v.z *. sf;
};;

let mul_vec_mat v m = {
  x = v.x *. m.m11 +. v.y *. m.m21 +. v.z *. m.m31;
  y = v.x *. m.m12 +. v.y *. m.m22 +. v.z *. m.m32;
  z = v.x *. m.m13 +. v.y *. m.m23 +. v.z *. m.m33;
};;

let mul_mat_mat a b =
  let p = mul_vec_mat {x = a.m11; y = a.m12; z = a.m13} b in
  let q = mul_vec_mat {x = a.m21; y = a.m22; z = a.m23} b in
  let r = mul_vec_mat {x = a.m31; y = a.m32; z = a.m33} b in
  {
    m11 = p.x; m12 = p.y; m13 = p.z;
    m21 = q.x; m22 = q.y; m23 = q.z;
    m31 = r.x; m32 = r.y; m33 = r.z;
  };;

let divide v = function
  | 0. -> {x = 0.; y = 0.; z = 0.}
  | k  -> {
      x = v.x /. k;
      y = v.y /. k;
      z = v.z /. k;
    };;

let dotProduct a b =
  a.x *. b.x +.
  a.y *. b.y +.
  a.z *. b.z;;

let crossProduct a b = {
  x = a.y *. b.z -. a.z *. b.y;
  y = a.z *. b.x -. a.x *. b.z;
  z = a.x *. b.y -. a.y *. b.x;
};;

let tripleProduct a b c = dotProduct a (crossProduct b c);;

let magnitude v =
  sqrt (
    v.x *. v.x +.
    v.y *. v.y +.
    v.z *. v.z);;

let distance a b = magnitude (subtract a b);;

let normalize v = divide v (magnitude v);;

let xAxisRotationMatrix theta =
  let st = sin theta in
  let ct = cos theta in
  {
    m11 = 1.; m12 = 0.; m13 = 0.;
    m21 = 0.; m22 = ct; m23 = st;
    m31 = 0.; m32 = st; m33 = ct;
  };;

let yAxisRotationMatrix theta =
  let st = sin theta in
  let ct = cos theta in
  {
    m11 = ct; m12 = 0.; m13 = st;
    m21 = 0.; m22 = 1.; m23 = 0.;
    m31 = st; m32 = 0.; m33 = ct;
  };;

let zAxisRotationMatrix theta =
  let st = sin theta in
  let ct = cos theta in
  {
    m11 =   ct; m12 = st; m13 = 0.;
    m21 = -.st; m22 = ct; m23 = 0.;
    m31 =   0.; m32 = 0.; m33 = 1.;
  };;

let axisRotationMatrix v theta =
  let v = normalize v in
  let st = sin theta in
  let ct = cos theta in
  let nc = 1. -. ct in
  {
    m11 = v.x *. v.x *. nc +. ct;
    m12 = v.x *. v.y *. nc +. v.z *. st;
    m13 = v.x *. v.z *. nc -. v.y *. st;
    m21 = v.x *. v.y *. nc -. v.x *. st;
    m22 = v.y *. v.y *. nc +. ct;
    m23 = v.y *. v.z *. nc +. v.x *. st;
    m31 = v.x *. v.z *. nc +. v.y *. st;
    m32 = v.y *. v.z *. nc -. v.x *. st;
    m33 = v.z *. v.z *. nc +. ct;
  };;

let scale_sf sf = {
  m11 = sf; m12 = 0.; m13 = 0.;
  m21 = 0.; m22 = sf; m23 = 0.;
  m31 = 0.; m32 = 0.; m33 = sf;
};;

let scale_xyz sx sy sz = {
  m11 = sx; m12 = 0.; m13 = 0.;
  m21 = 0.; m22 = sy; m23 = 0.;
  m31 = 0.; m32 = 0.; m33 = sz;
};;

let scale_axis v sf =
  let v = normalize v in
  let sf = sf -. 1. in
  {
    m11 = sf *. v.x *. v.x +. 1.;
    m12 = sf *. v.x *. v.y;
    m13 = sf *. v.x *. v.z;
    m21 = sf *. v.x *. v.y;
    m22 = sf *. v.y *. v.y +. 1.;
    m23 = sf *. v.y *. v.z;
    m31 = sf *. v.x *. v.z;
    m32 = sf *. v.y *. v.z;
    m33 = sf *. v.z *. v.z +. 1.;
  };;

let orthoProjection v = scale_axis v 0.;;

let reflection v = scale_axis v (-1.);;
