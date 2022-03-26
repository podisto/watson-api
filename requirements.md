## Speech 2 Text

curl -X POST -u "apiKey:g3zIYUolm2Uz7uM7AubQfLq1CHW1af1E0tScC_YYyLCW" --header "Content-Type:audio/flac" --data-binary @audio-file2.flac "https://api.eu-de.speech-to-text.watson.cloud.ibm.com/instances/a767c987-f49c-4717-98f3-df5a86beef6c/v1/recognize"

curl -X POST -u "apiKey:g3zIYUolm2Uz7uM7AubQfLq1CHW1af1E0tScC_YYyLCW" --header "Content-Type:audio/ogg" --data-binary @ged.ogg "https://api.eu-de.speech-to-text.watson.cloud.ibm.com/instances/a767c987-f49c-4717-98f3-df5a86beef6c/v1/recognize"

curl --verbose -XPOST -u "apiKey:g3zIYUolm2Uz7uM7AubQfLq1CHW1af1E0tScC_YYyLCW" --header "Content-Type:audio/ogg" --data-binary @test.ogg "https://api.eu-de.speech-to-text.watson.cloud.ibm.com/instances/a767c987-f49c-4717-98f3-df5a86beef6c/v1/recognize"


curl -v -X POST -u "apiKey:g3zIYUolm2Uz7uM7AubQfLq1CHW1af1E0tScC_YYyLCW" ^
--header "Content-Type:audio/flac" ^
--data-binary @audio-file2.flac ^
"https://api.eu-de.speech-to-text.watson.cloud.ibm.com/instances/a767c987-f49c-4717-98f3-df5a86beef6c/v1/recognize"

1. "transcript": "bonsoir achat je souhaite accéder à mes comptes " => souhaite, acceder, comptes = lister ses comptes
2. "transcript": "je souhaite connaitre le solde de mon compte " => souhaite, connaitre, solde, compte = afficher solde du compte
3. "transcript": "je souhaite connaitre les dernières opérations " => souhaite, connaitre, dernieres, operations = afficher ses dernières operations
4. "transcript": "souhaite connaitre le solde de ma carte prépayée " => souhaite, connaitre, solde, carte, prepayee = afficher le solde de la carte prepayee



## Text 2 Speech

curl -X GET -u "apikey:t1y_W2RrHFmv-gl0Xb4KuOXqcqO3LNBYgnbSVwRStSe5" "https://api.eu-de.text-to-speech.watson.cloud.ibm.com/instances/effe053c-f02a-4a39-8339-d6d181ef71fe/v1/voices"

curl -X GET -u "apikey:t1y_W2RrHFmv-gl0Xb4KuOXqcqO3LNBYgnbSVwRStSe5" --output hello_world.wav "https://api.eu-de.text-to-speech.watson.cloud.ibm.com/instances/effe053c-f02a-4a39-8339-d6d181ef71fe/v1/synthesize?accept=audio%2Fwav&text=Hello%20world&voice=en-US_AllisonV3Voice"

curl -X POST -u "apikey:t1y_W2RrHFmv-gl0Xb4KuOXqcqO3LNBYgnbSVwRStSe5" --header "Content-Type: application/json" --header "Accept: audio/wav" --data "{\"text\":\"Hello world\"}" --output hello_world.wav "https://api.eu-de.text-to-speech.watson.cloud.ibm.com/instances/effe053c-f02a-4a39-8339-d6d181ef71fe/v1/synthesize?voice=en-US_AllisonV3Voice"