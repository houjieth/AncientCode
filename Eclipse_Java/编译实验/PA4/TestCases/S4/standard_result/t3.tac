FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T2 _T3 _T4 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T2 = 4 [ _T2 ]
    parm _T2 [ ]
    _T3 =  call _Alloc [ _T3 ]
    _T4 = VTBL <_Main> [ _T3 _T4 ]
    *(_T3 + 0) = _T4 [ _T3 ]
END BY RETURN, result = _T3

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T5 _T6 _T7 _T8 _T9 _T10 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T7 = "hello" [ _T7 ]
    _T6 = _T7 [ _T6 ]
    _T8 = 4 [ _T6 _T8 ]
    _T9 = 5 [ _T6 _T8 _T9 ]
    parm _T8 [ _T6 _T9 ]
    parm _T9 [ _T6 ]
    _T10 =  call _Main.test [ _T6 _T10 ]
    _T5 = _T10 [ _T5 _T6 ]
    parm _T5 [ _T6 ]
    call _PrintInt [ _T6 ]
    parm _T6 [ ]
    call _PrintString [ ]
END BY RETURN, void result

FUNCTION _Main.test : 
BASIC BLOCK 0 : 
  Def     = [ _T11 ]
  liveUse = [ _T0 _T1 ]
  liveIn  = [ _T0 _T1 ]
  liveOut = [ ]
    _T11 = (_T0 + _T1) [ _T11 ]
END BY RETURN, result = _T11

