package DCMS_CORBA;

/**
* DCMS_CORBA/DCMSHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DCMS_CORBA.idl
* Monday, July 10, 2017 1:30:45 PM EDT
*/

public final class DCMSHolder implements org.omg.CORBA.portable.Streamable
{
  public DCMS_CORBA.DCMS value = null;

  public DCMSHolder ()
  {
  }

  public DCMSHolder (DCMS_CORBA.DCMS initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = DCMS_CORBA.DCMSHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    DCMS_CORBA.DCMSHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return DCMS_CORBA.DCMSHelper.type ();
  }

}
