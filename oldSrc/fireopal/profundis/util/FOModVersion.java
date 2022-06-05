package fireopal.profundis.util;

public class FOModVersion {
    public final int major;
    public final int minor;
    public final int patch;   
    
    public FOModVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public FOModVersion(String version) {
        boolean index0 = false;
        int[] indexes = new int[2]; 

        for (int i = 0; i < version.length(); i += 1) {
            if (version.charAt(i) == '.') {
                if (!index0) {
                    indexes[0] = i;
                    index0 = true;
                } else {
                    indexes[1] = i;
                }
            }
        }

        this.major = substringToInt(version, 0, indexes[0]);
        this.minor = substringToInt(version, indexes[0] + 1, indexes[1]);
        this.patch = substringToInt(version, indexes[1] + 1, version.length());
    }

    private static final int substringToInt(String str, int index1, int index2) {    
        return Integer.parseInt(
            str.substring(index1, index2)
        );
    }
    
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    public static FOModVersion fromString(String version) {
        return new FOModVersion(version);
    }
}
