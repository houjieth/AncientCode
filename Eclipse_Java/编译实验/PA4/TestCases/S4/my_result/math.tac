FUNCTION _Math_New : 
BASIC BLOCK 0 : 
  Def     = [ _T8 _T9 _T10 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T8 = 4 [ _T8 ]
    parm _T8 [ ]
    _T9 =  call _Alloc [ _T9 ]
    _T10 = VTBL <_Math> [ _T9 _T10 ]
    *(_T9 + 0) = _T10 [ _T9 ]
END BY RETURN, result = _T9

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T11 _T12 _T13 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T11 = 4 [ _T11 ]
    parm _T11 [ ]
    _T12 =  call _Alloc [ _T12 ]
    _T13 = VTBL <_Main> [ _T12 _T13 ]
    *(_T12 + 0) = _T13 [ _T12 ]
END BY RETURN, result = _T12

FUNCTION _Math.abs : 
BASIC BLOCK 0 : 
  Def     = [ _T14 _T15 ]
  liveUse = [ _T0 ]
  liveIn  = [ _T0 ]
  liveOut = [ _T0 ]
    _T14 = 0 [ _T0 _T14 ]
    _T15 = (_T0 >= _T14) [ _T0 _T15 ]
END BY JZERO, if _T15 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ ]
  liveUse = [ _T0 ]
  liveIn  = [ _T0 ]
  liveOut = [ ]
END BY RETURN, result = _T0
BASIC BLOCK 2 : 
  Def     = [ _T16 ]
  liveUse = [ _T0 ]
  liveIn  = [ _T0 ]
  liveOut = [ ]
    _T16 = - _T0 [ _T16 ]
END BY RETURN, result = _T16
BASIC BLOCK 3 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Math.pow : 
BASIC BLOCK 0 : 
  Def     = [ _T17 _T18 _T19 _T20 ]
  liveUse = [ ]
  liveIn  = [ _T1 _T2 ]
  liveOut = [ _T1 _T2 _T17 _T18 ]
    _T19 = 1 [ _T1 _T2 _T19 ]
    _T18 = _T19 [ _T1 _T2 _T18 ]
    _T20 = 0 [ _T1 _T2 _T18 _T20 ]
    _T17 = _T20 [ _T1 _T2 _T17 _T18 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T17 _T21 _T22 ]
  liveUse = [ _T17 ]
  liveIn  = [ _T1 _T2 _T17 _T18 ]
  liveOut = [ _T1 _T2 _T17 _T18 ]
    _T21 = 1 [ _T1 _T2 _T17 _T18 _T21 ]
    _T22 = (_T17 + _T21) [ _T1 _T2 _T18 _T22 ]
    _T17 = _T22 [ _T1 _T2 _T17 _T18 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T23 ]
  liveUse = [ _T2 _T17 ]
  liveIn  = [ _T1 _T2 _T17 _T18 ]
  liveOut = [ _T1 _T2 _T17 _T18 ]
    _T23 = (_T17 < _T2) [ _T1 _T2 _T17 _T18 _T23 ]
END BY JZERO, if _T23 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T18 _T24 ]
  liveUse = [ _T1 _T18 ]
  liveIn  = [ _T1 _T2 _T17 _T18 ]
  liveOut = [ _T1 _T2 _T17 _T18 ]
    _T24 = (_T18 * _T1) [ _T1 _T2 _T17 _T24 ]
    _T18 = _T24 [ _T1 _T2 _T17 _T18 ]
END BY JUMP, goto 1
BASIC BLOCK 4 : 
  Def     = [ ]
  liveUse = [ _T18 ]
  liveIn  = [ _T18 ]
  liveOut = [ ]
END BY RETURN, result = _T18

FUNCTION _Math.log : 
BASIC BLOCK 0 : 
  Def     = [ _T25 _T26 ]
  liveUse = [ _T3 ]
  liveIn  = [ _T3 ]
  liveOut = [ _T3 ]
    _T25 = 1 [ _T3 _T25 ]
    _T26 = (_T3 < _T25) [ _T3 _T26 ]
END BY JZERO, if _T26 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ _T27 _T28 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T27 = 1 [ _T27 ]
    _T28 = - _T27 [ _T28 ]
END BY RETURN, result = _T28
BASIC BLOCK 2 : 
  Def     = [ _T29 _T30 ]
  liveUse = [ ]
  liveIn  = [ _T3 ]
  liveOut = [ _T3 _T29 ]
    _T30 = 0 [ _T3 _T30 ]
    _T29 = _T30 [ _T3 _T29 ]
END BY JUMP, goto 3
BASIC BLOCK 3 : 
  Def     = [ _T31 _T32 ]
  liveUse = [ _T3 ]
  liveIn  = [ _T3 _T29 ]
  liveOut = [ _T3 _T29 ]
    _T31 = 1 [ _T3 _T29 _T31 ]
    _T32 = (_T3 > _T31) [ _T3 _T29 _T32 ]
END BY JZERO, if _T32 = 
    0 : goto 5; 1 : goto 4
BASIC BLOCK 4 : 
  Def     = [ _T3 _T29 _T33 _T34 _T35 _T36 ]
  liveUse = [ _T3 _T29 ]
  liveIn  = [ _T3 _T29 ]
  liveOut = [ _T3 _T29 ]
    _T33 = 1 [ _T3 _T29 _T33 ]
    _T34 = (_T29 + _T33) [ _T3 _T34 ]
    _T29 = _T34 [ _T3 _T29 ]
    _T35 = 2 [ _T3 _T29 _T35 ]
    _T36 = (_T3 / _T35) [ _T29 _T36 ]
    _T3 = _T36 [ _T3 _T29 ]
END BY JUMP, goto 3
BASIC BLOCK 5 : 
  Def     = [ ]
  liveUse = [ _T29 ]
  liveIn  = [ _T29 ]
  liveOut = [ ]
END BY RETURN, result = _T29

FUNCTION _Math.max : 
BASIC BLOCK 0 : 
  Def     = [ _T37 ]
  liveUse = [ _T4 _T5 ]
  liveIn  = [ _T4 _T5 ]
  liveOut = [ _T4 _T5 ]
    _T37 = (_T4 > _T5) [ _T4 _T5 _T37 ]
END BY JZERO, if _T37 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ ]
  liveUse = [ _T4 ]
  liveIn  = [ _T4 ]
  liveOut = [ ]
END BY RETURN, result = _T4
BASIC BLOCK 2 : 
  Def     = [ ]
  liveUse = [ _T5 ]
  liveIn  = [ _T5 ]
  liveOut = [ ]
END BY RETURN, result = _T5
BASIC BLOCK 3 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Math.min : 
BASIC BLOCK 0 : 
  Def     = [ _T38 ]
  liveUse = [ _T6 _T7 ]
  liveIn  = [ _T6 _T7 ]
  liveOut = [ _T6 _T7 ]
    _T38 = (_T6 < _T7) [ _T6 _T7 _T38 ]
END BY JZERO, if _T38 = 
    0 : goto 2; 1 : goto 1
BASIC BLOCK 1 : 
  Def     = [ ]
  liveUse = [ _T6 ]
  liveIn  = [ _T6 ]
  liveOut = [ ]
END BY RETURN, result = _T6
BASIC BLOCK 2 : 
  Def     = [ ]
  liveUse = [ _T7 ]
  liveIn  = [ _T7 ]
  liveOut = [ ]
END BY RETURN, result = _T7
BASIC BLOCK 3 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T39 _T40 _T41 _T42 _T43 _T44 _T45 _T46 _T47 _T48 _T49 _T50 _T51 _T52 _T53 _T54 _T55 _T56 _T57 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T39 = 1 [ _T39 ]
    _T40 = - _T39 [ _T40 ]
    parm _T40 [ ]
    _T41 =  call _Math.abs [ _T41 ]
    parm _T41 [ ]
    call _PrintInt [ ]
    _T42 = "\n" [ _T42 ]
    parm _T42 [ ]
    call _PrintString [ ]
    _T43 = 2 [ _T43 ]
    _T44 = 3 [ _T43 _T44 ]
    parm _T43 [ _T44 ]
    parm _T44 [ ]
    _T45 =  call _Math.pow [ _T45 ]
    parm _T45 [ ]
    call _PrintInt [ ]
    _T46 = "\n" [ _T46 ]
    parm _T46 [ ]
    call _PrintString [ ]
    _T47 = 16 [ _T47 ]
    parm _T47 [ ]
    _T48 =  call _Math.log [ _T48 ]
    parm _T48 [ ]
    call _PrintInt [ ]
    _T49 = "\n" [ _T49 ]
    parm _T49 [ ]
    call _PrintString [ ]
    _T50 = 1 [ _T50 ]
    _T51 = 2 [ _T50 _T51 ]
    parm _T50 [ _T51 ]
    parm _T51 [ ]
    _T52 =  call _Math.max [ _T52 ]
    parm _T52 [ ]
    call _PrintInt [ ]
    _T53 = "\n" [ _T53 ]
    parm _T53 [ ]
    call _PrintString [ ]
    _T54 = 1 [ _T54 ]
    _T55 = 2 [ _T54 _T55 ]
    parm _T54 [ _T55 ]
    parm _T55 [ ]
    _T56 =  call _Math.min [ _T56 ]
    parm _T56 [ ]
    call _PrintInt [ ]
    _T57 = "\n" [ _T57 ]
    parm _T57 [ ]
    call _PrintString [ ]
END BY RETURN, void result

