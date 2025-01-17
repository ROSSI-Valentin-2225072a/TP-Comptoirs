package comptoirs.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Commande;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    @Query("""
        select SUM(l.produit.prixUnitaire * l.quantite * (1 - l.commande.remise))
        from Ligne l
        where l.commande.numero = :numeroCommande
        """)
    BigDecimal montantArticles(Integer numeroCommande);

    @Query(value = """
        select l.commande.numero as numero, l.commande.port as port, SUM(l.produit.prixUnitaire * l.quantite * (1 - l.commande.remise)) as montant
        from Ligne l
        group by l.commande.numero
        having l.commande.client.code=:codeClient
        """)
    List<MontantParCommandeClient> montantsCommandesParClient(String codeClient);

    }
