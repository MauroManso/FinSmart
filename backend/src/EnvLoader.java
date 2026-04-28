import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class EnvLoader {

    private static final Map<String, String> ENV_VARS = new HashMap<>();

    static {
        loadEnvFile();
    }

    private EnvLoader() {
    }

    private static void loadEnvFile() {
        File envFile = findEnvFile();
        if (envFile == null || !envFile.exists()) {
            System.err.println("Aviso: arquivo .env não encontrado. Usando variáveis de ambiente do sistema.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                int idx = line.indexOf('=');
                if (idx > 0) {
                    String key = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim();
                    ENV_VARS.put(key, value);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler .env: " + e.getMessage());
        }
    }

    private static File findEnvFile() {
        String[] paths = {
                ".env",
                "../.env",
                "../../.env",
                System.getProperty("user.dir") + File.separator + ".env"
        };

        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                System.out.println("Arquivo .env carregado de: " + file.getAbsolutePath());
                return file;
            }
        }

        return null;
    }

    public static String get(String key, String defaultValue) {
        String value = ENV_VARS.get(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        value = System.getenv(key);
        if (value != null && !value.isBlank()) {
            return value;
        }

        return defaultValue;
    }

    public static String get(String key) {
        return get(key, null);
    }
}
