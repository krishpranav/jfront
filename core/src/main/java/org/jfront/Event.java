package org.jfront;

public abstract class Event {

    public static final int F_PREVENT_DEFAULT = 1 << 0;

    public static class Input extends Event {
        public int flags;

        public final double time;

        public boolean isAltDown ()
        {
            return isSet(F_ALT_DOWN);
        }

        public boolean isCtrlDown ()
        {
            return isSet(F_CTRL_DOWN);
        }

        public boolean isShiftDown ()
        {
            return isSet(F_SHIFT_DOWN);
        }

        public boolean isMetaDown ()
        {
            return isSet(F_META_DOWN);
        }

        public boolean isSet (int flag)
        {
            return (flags & flag) != 0;
        }

        public void setFlag (int flag)
        {
            flags |= flag;
        }

        public void clearFlag (int flag)
        {
            flags &= ~flag;
        }

        public void updateFlag (int flag, boolean on)
        {
            if (on) setFlag(flag);
            else clearFlag(flag);
        }

        @Override public String toString ()
        {
            StringBuilder builder = new StringBuilder(name()).append('[');
            addFields(builder);
            return builder.append(']').toString();
        }

        public static int modifierFlags (boolean altP, boolean ctrlP, boolean metaP, boolean shiftP)
        {
            int flags = 0;
            if (altP)   flags |= F_ALT_DOWN;
            if (ctrlP)  flags |= F_CTRL_DOWN;
            if (metaP)  flags |= F_META_DOWN;
            if (shiftP) flags |= F_SHIFT_DOWN;
            return flags;
        }

        protected Input (int flags, double time)
        {
            this.flags = flags;
            this.time = time;
        }

        protected String name ()
        {
            return "Input";
        }

        protected void addFields (StringBuilder builder)
        {
            builder.append("time=").append(time).append(", flags=").append(flags);
        }
    }

    public static class XY extends Input implements pythagoras.f.XY
    {

        public final float x;

        public final float y;

        @Override public float x ()
        {
            return x;
        }
        @Override public float y ()
        {
            return y;
        }

        protected XY (int flags, double time, float x, float y)
        {
            super(flags, time);
            this.x = x;
            this.y = y;
        }

        @Override protected String name ()
        {
            return "XY";
        }

        @Override protected void addFields (StringBuilder builder)
        {
            super.addFields(builder);
            builder.append(", x=").append(x).append(", y=").append(y);
        }
    }

    protected static final int F_ALT_DOWN   = 1 << 1;
    protected static final int F_CTRL_DOWN  = 1 << 2;
    protected static final int F_SHIFT_DOWN = 1 << 3;
    protected static final int F_META_DOWN  = 1 << 4;
}