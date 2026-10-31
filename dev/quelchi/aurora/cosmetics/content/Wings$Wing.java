package dev.quelchi.aurora.cosmetics.content;

import dev.quelchi.aurora.cosmetics.content.Colorways$Way;
import dev.quelchi.aurora.cosmetics.content.Cosmetics;
import java.util.List;
import java.util.Map;

public record Wings$Wing(String id, String label, String model, List<Colorways$Way> ways, Map<String, String> models) {
    public String model(String string) {
        String string2;
        block9: {
            block10: {
                String string3;
                int n;
                block8: {
                    String string4;
                    block6: {
                        block7: {
                            n = Cosmetics.a;
                            string4 = string;
                            if (n != 0) break block6;
                            if (string4 != null) break block7;
                            string3 = null;
                            break block8;
                        }
                        string4 = this.models.get(string);
                    }
                    string3 = string4;
                }
                String string5 = string3;
                string2 = string5;
                if (n != 0) break block9;
                if (string2 == null) break block10;
                string2 = string5;
                break block9;
            }
            string2 = this.model;
        }
        return string2;
    }
}
