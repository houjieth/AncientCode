FUNCTION _Computer_New : 
BASIC BLOCK 0 : 
  Def     = [ _T2 _T3 _T4 _T5 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T2 = 8 [ _T2 ]
    parm _T2 [ ]
    _T3 =  call _Alloc [ _T3 ]
    _T4 = 0 [ _T3 _T4 ]
    *(_T3 + 4) = _T4 [ _T3 ]
    _T5 = VTBL <_Computer> [ _T3 _T5 ]
    *(_T3 + 0) = _T5 [ _T3 ]
END BY RETURN, result = _T3

FUNCTION _Mac_New : 
BASIC BLOCK 0 : 
  Def     = [ _T8 _T9 _T10 _T11 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T8 = 12 [ _T8 ]
    parm _T8 [ ]
    _T9 =  call _Alloc [ _T9 ]
    _T10 = 0 [ _T9 _T10 ]
    *(_T9 + 4) = _T10 [ _T9 _T10 ]
    *(_T9 + 8) = _T10 [ _T9 ]
    _T11 = VTBL <_Mac> [ _T9 _T11 ]
    *(_T9 + 0) = _T11 [ _T9 ]
END BY RETURN, result = _T9

FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T12 _T13 _T14 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T12 = 4 [ _T12 ]
    parm _T12 [ ]
    _T13 =  call _Alloc [ _T13 ]
    _T14 = VTBL <_Main> [ _T13 _T14 ]
    *(_T13 + 0) = _T14 [ _T13 ]
END BY RETURN, result = _T13

FUNCTION _Computer.Crash : 
BASIC BLOCK 0 : 
  Def     = [ _T15 _T16 ]
  liveUse = [ ]
  liveIn  = [ _T1 ]
  liveOut = [ _T1 _T15 ]
    _T16 = 0 [ _T1 _T16 ]
    _T15 = _T16 [ _T1 _T15 ]
END BY JUMP, goto 2
BASIC BLOCK 1 : 
  Def     = [ _T15 _T17 _T18 ]
  liveUse = [ _T15 ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ _T1 _T15 ]
    _T17 = 1 [ _T1 _T15 _T17 ]
    _T18 = (_T15 + _T17) [ _T1 _T18 ]
    _T15 = _T18 [ _T1 _T15 ]
END BY JUMP, goto 2
BASIC BLOCK 2 : 
  Def     = [ _T19 ]
  liveUse = [ _T1 _T15 ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ _T1 _T15 ]
    _T19 = (_T15 < _T1) [ _T1 _T15 _T19 ]
END BY JZERO, if _T19 = 
    0 : goto 4; 1 : goto 3
BASIC BLOCK 3 : 
  Def     = [ _T20 ]
  liveUse = [ ]
  liveIn  = [ _T1 _T15 ]
  liveOut = [ _T1 _T15 ]
    _T20 = "sad\n" [ _T1 _T15 _T20 ]
    parm _T20 [ _T1 _T15 ]
    call _PrintString [ _T1 _T15 ]
END BY JUMP, goto 1
BASIC BLOCK 4 : 
  Def     = [ ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
END BY RETURN, void result

FUNCTION _Mac.Crash : 
BASIC BLOCK 0 : 
  Def     = [ _T21 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T21 = "ack!" [ _T21 ]
    parm _T21 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T22 _T23 _T24 _T25 _T26 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T23 =  call _Mac_New [ _T23 ]
    _T22 = _T23 [ _T22 ]
    _T24 = 2 [ _T22 _T24 ]
    parm _T22 [ _T22 _T24 ]
    parm _T24 [ _T22 ]
    _T25 = *(_T22 + 0) [ _T25 ]
    _T26 = *(_T25 + 0) [ _T26 ]
    call _T26 [ ]
END BY RETURN, void result

