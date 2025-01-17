package comptoirs.dao;

import java.math.BigDecimal;

public interface MontantParCommandeClient {
    Integer getNumero();
    Integer getPort();
    BigDecimal getMontant();
}
