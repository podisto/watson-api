package ma.sg.hackathon.watsonapi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

/**
 * Created by podisto on 28/03/2022.
 */
public class TransactionDictionary {

    private static final Map<String, List<String>> responses = new HashMap<>();

    private static final List<String> transactionKeywords = asList(
            "je veux faire un virement",
            "recharger une carte",
            "payer une facture",
            "faire une mise à disposition",
            "souscrire un produit",
            "virement",
            "recharger carte",
            "paiement",
            "payement",
            "payer",
            "disposition",
            "souscrire",
            "produit",
            "bénéficiaire",
            "virement tiers",
            "tiers",
            "virement compte à compte",
            "virement compte a compte",
            "virement compte",
            "virement compte",
            "je veux réaliser un virement",
            "je veux envoyer de l'argent",
            "envoyer",
            "je veux transmettre de l'argent",
            "je veux transmettre un virement",
            "réaliser un virement",
            "exécuter un virement",
            "un virement",
            "faire un virement",
            "envoyer un virement",
            "transmettre un virement",
            "envoyer de l'argent",
            "transmettre de l'argent",
            "j'aimerais faire un virement",
            "j'aimerai faire un virement",
            "je souhaite faire un virement",
            "recherchez une carte",
            "paye une facture"
    );

    private static final List<String> balancesKeywords = asList(
            "solde", "soldes", "solde actuel", "solde aujourd'hui", "je veux accéder à mes soldes", "je veux accéder à mon solde",
            "je veux consulter mon solde", "je veux accéder aux soldes", "je veux accéder au solde", "je veux voir mon solde",
            "je veux voir mes soldes", "consulter solde", "accéder solde", "entendre solde", "savoir solde", "je veux consulter mes comptes",
            "je veux accéder à mon compte", "contes", "je veux voir mon compte", "je peux voir mes comptes", "consulter compte", "accéder compte",
            "solde compte", "solde actuelle", "solde actuel", "solde aujourdhui", "j'veux voir mes soldes", "consulter soldes", "saunt"
    );

    static {
        responses.put("TRANSACTION", transactionKeywords);
        responses.put("CONSULTATION_SOLDE", balancesKeywords);
    }

    public static String getResponse(String transcript) {
        return responses.entrySet()
                .stream()
                .filter(response -> response.getValue().contains(transcript.trim().toLowerCase()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new ChoiceNotFoundException("Unable to process your request " + transcript));
    }
}
