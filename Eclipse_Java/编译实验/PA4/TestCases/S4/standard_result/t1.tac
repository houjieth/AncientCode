FUNCTION _Main_New : 
BASIC BLOCK 0 : 
  Def     = [ _T0 _T1 _T2 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T0 = 4 [ _T0 ]
    parm _T0 [ ]
    _T1 =  call _Alloc [ _T1 ]
    _T2 = VTBL <_Main> [ _T1 _T2 ]
    *(_T1 + 0) = _T2 [ _T1 ]
END BY RETURN, result = _T1

FUNCTION main : 
BASIC BLOCK 0 : 
  Def     = [ _T3 ]
  liveUse = [ ]
  liveIn  = [ ]
  liveOut = [ ]
    _T3 = "hello world" [ _T3 ]
    parm _T3 [ ]
    call _PrintString [ ]
END BY RETURN, void result

