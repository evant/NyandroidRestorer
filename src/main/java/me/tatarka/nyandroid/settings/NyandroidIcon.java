package me.tatarka.nyandroid.settings;

import java.util.Arrays;
import java.util.Objects;

public class NyandroidIcon {

    public static final NyandroidIcon[] ICONS = {
            new NyandroidIcon("logcat", "Classic", "/file-icons/logcat.png"),
            new NyandroidIcon("logcat_rainbow", "Rainbow", "/file-icons/logcat_rainbow.svg"),
            new NyandroidIcon("logcat_trans", "Trans", "/file-icons/logcat_trans.svg")
    };

    public static NyandroidIcon of(String id) {
        return Arrays.stream(ICONS).filter((icon) -> icon.id.equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected icon id: " + id));
    }

    public final String id;
    public final String name;
    public final String path;

    public NyandroidIcon(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NyandroidIcon that = (NyandroidIcon) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
