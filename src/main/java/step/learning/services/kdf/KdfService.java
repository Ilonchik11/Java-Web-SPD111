package step.learning.services.kdf;
/**
 * Key Derivation Function service
 * By RFC 2898
*/

public interface KdfService {
    String derivedKey(String password, String salt);
}
