package mythware.utils;


import com.github.bingoohuang.westid.WestId;

public interface Id {

    static String next() {
        return WestId.next() + "";
    }

}
