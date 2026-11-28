---
title: Παρακολούθηση WanbExpress: κωδικός WNB…YQ και στάδια
description: Εντοπίστε δέμα WanbExpress (Wanb Express / WANB) από την Κίνα: μορφή WNB…YQ, εξήγηση σταδίων, τοπικοί μεταφορείς (iMile, InPost, Poste Italiane) και τελωνείο.
slug: wanbexpress-entopismos-dematos
published: 2026-11-27
updated: 2026-11-27
---

Η WanbExpress (Wanb Express, WanBang Express ή WANB) είναι κινεζικός διασυνοριακός μεταφορέας του οποίου τα σκαναρίσματα ακολουθούν τα δέματα από την Κίνα, μέσα από την πτήση και το τελωνείο, προτού τα παραδώσει σε τοπικούς μεταφορείς όπως οι iMile (Γερμανία), InPost (Ηνωμένο Βασίλειο) ή Poste Italiane (Ιταλία). Επικολλήστε τον κωδικό `WNB…YQ` των 17 χαρακτήρων στο πεδίο παραπάνω ή ελέγξτε στο `tracking.wanbexpress.com` (με προστασία reCAPTCHA) για να δείτε τα σκαναρίσματα και τον αριθμό παρακολούθησης του τοπικού μεταφορέα.

## Αριθμός παρακολούθησης WanbExpress: πώς μοιάζει

Οι φορτωτικές της WanbExpress αποτελούνται από `WNB`, δύο γράμματα, δέκα ψηφία και `YQ`, συνολικά 17 χαρακτήρες. Με επινοημένα ψηφία:

| Αριθμός | Τι είναι |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, δύο γράμματα, δέκα ψηφία και `YQ`: φορτωτική WanbExpress. |
| `WNBKG482910573YQ` | Μόνο εννέα ψηφία: δεν ταιριάζει στη μορφή της WanbExpress. Ελέγξτε αν λείπει ένα ψηφίο. |
| `JSEKG4829105736YQ` | Τελειώνει επίσης σε `YQ`, αλλά ξεκινά με `JSE`: αριθμός [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Ξεκινά με `JXC`: αριθμός [Juxi Express](carrier:juxi-express). |

Αρκετοί Κινέζοι διαμεταφορείς τελειώνουν τους κωδικούς τους σε `YQ`, καθένας με τα δικά του αρχικά γράμματα: το `WNB` ξεχωρίζει τη WanbExpress. Τα κενά, οι παύλες και τα πεζά γράμματα δεν έχουν σημασία στο Peek.

**Πού θα τον βρείτε.** Το κατάστημα στέλνει τον αριθμό στο email αποστολής ή στη σελίδα της παραγγελίας σας. Αν δεν τον βρίσκετε, δείτε [πώς να βρείτε τον αριθμό παρακολούθησης](guide:find-tracking-number) (στα Αγγλικά).

**Σύνδεσμος παρακολούθησης.** Ένας σύνδεσμος προς τη σελίδα της WanbExpress που τελειώνει σε `?trackingNumbers=` και έναν κωδικό ανοίγει το συγκεκριμένο δέμα. Ο αριθμός `WNB` είναι το τμήμα μετά το `=`: αντιγράψτε τον από εκεί στο Peek.

**Δεύτερος αριθμός.** Το σύστημα της WanbExpress μπορεί επίσης να εμφανίσει τον αριθμό που χρησιμοποιεί ο τοπικός μεταφορέας για το δέμα. Αυτός ο αριθμός ανήκει στον άλλο μεταφορέα: ελέγξτε τον στο δικό του σύστημα ή προσθέστε τον στο Peek ως ξεχωριστό δέμα.

## Επεξήγηση των σταδίων παρακολούθησης WanbExpress

Τα σκαναρίσματα της WanbExpress είναι στα Αγγλικά, καθένα με ώρα και μερικές φορές τοποθεσία. Με τη σειρά που τα καταγράφει ο κώδικας του Peek:

| Τι γράφει η WanbExpress | Τι σημαίνει για εσάς | Τι δείχνει το Peek |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | Το κατάστημα έκλεισε την αποστολή και έστειλε τα στοιχεία. Η WanbExpress δεν έχει ακόμη το δέμα. | «Label created» |
| `Parcel received at Shenzhen Sorting Centre.` | Το κέντρο διαλογής στη Σεντζέν της Κίνας παρέλαβε το δέμα: το πρώτο φυσικό σκανάρισμα. | «Received by carrier» |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Ταξινομήθηκε και αναχωρεί από το κέντρο διαλογής. | «In transit» |
| `Customs declaration completed` | Ολοκληρώθηκε η τελωνειακή διασάφηση. Η διατύπωση δεν αναφέρει ποιας χώρας και δεν είναι ο τελικός εκτελωνισμός (`Custom clearance completed`). | «In transit» |
| `Parcel departure in Transit Facility, Parcel Departure` | Αναχώρησε από ενδιάμεση εγκατάσταση. | «In transit» |
| `Parcel is leaving Airport, Parcel Leaving Port` | Αναχωρεί με την πτήση προς τη χώρα προορισμού. | «In transit» |
| `Parcel arrives at Airport, Parcel Arrival` | Έφτασε αεροπορικώς στη χώρα σας ή κοντά σε αυτήν. Ακολουθεί το τελωνείο. | «In transit» |
| `Custom clearance completed` | Ολοκληρώθηκε ο εκτελωνισμός εισαγωγής. | «In transit» |
| `Parcel received by in JFK01.` | Η WanbExpress το καταχωρεί ως παραλαβή για το τελευταίο σκέλος, χωρίς να αναφέρει ποιος το παρέλαβε. | «In transit» |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Ειδοποίηση συνεργάτη με δικά του λόγια, χωρίς κωδικό WanbExpress: το δέμα δεν έχει παραδοθεί ακόμη σε αυτόν. | «Label created» ή μεταγενέστερο στάδιο αν έχει ήδη κινηθεί |
| `In transit along Poste Italiane’s logistic network` | Σε διακίνηση μέσω του δικτύου του τοπικού μεταφορέα, εδώ της [Poste Italiane](carrier:poste-italiane). | «In transit» |
| `Parcel is out for delivery.` | Σε διανομή προς εσάς. | «Out for delivery» |
| `Consignee absent, Consignee could not be found.` | Ο διανομέας του συνεργάτη δεν βρήκε κανέναν. | «Delivery attempted» |
| `Placed in Parcelshop/Parcel locker` | Μετά την αποτυχημένη προσπάθεια, ο συνεργάτης άφησε το δέμα σε σημείο παραλαβής ή θυρίδα. | «Ready for pickup» |
| `Parcel has been delivered.(Front door)` | Παραδόθηκε. Το `(Front door)` δηλώνει πού αφέθηκε. | «Delivered» |

**Τα σκαναρίσματα του τοπικού μεταφορέα.** Μετά την πτήση, το σύστημα της WanbExpress μεταφέρει τα σκαναρίσματα του τοπικού μεταφορέα με τα δικά του λόγια, συνήθως χωρίς κωδικό WanbExpress. Σε δέμα προς τη Γερμανία, το ιστορικό δείχνει την [iMile](carrier:imile), με κάθε γραμμή μετά το όνομα του κέντρου ή σταθμού της σε αγκύλες 【…】:

| Τι γράφει η iMile | Τι σημαίνει για εσάς | Τι δείχνει το Peek |
| --- | --- | --- |
| `Received.` | Το κέντρο της iMile στη χώρα προορισμού παρέλαβε το δέμα. | «In transit» |
| `Arrived.` | Στον σταθμό διανομής που εξυπηρετεί την περιοχή σας. | «In transit» |
| `Dispatch task assigned to DA.` | Ορίστηκε διανομέας (DA, delivery associate) για τη διαδρομή, κάτι που μπορεί να γίνει ώρες πριν ξεκινήσει. | «In transit» |
| `Shipment out for delivery.` | Σε διανομή. | «Out for delivery» |
| `<Route not completed>`, `Back To DS.` | Η διαδρομή έληξε χωρίς παράδοση και το δέμα επέστρεψε στον σταθμό διανομής (DS). | «Delivery attempted» |
| `Scheduled to <2026-11-02> .` | Η ημερομηνία που ορίστηκε για την επόμενη προσπάθεια μετά την επιστροφή στον σταθμό. | Παραμένει «Delivery attempted» |

Κινήσεις άλλων συνεργατών εμφανίζονται παρόμοια. Σε δέματα προς τις ΗΠΑ, οι γραμμές `Parcel received by Transit Facility.`, `In Processing at Transit Facility.`, `In Transit to Next Facility.` και `Parcel received by local Facility.` περιγράφουν την παραλαβή, τη διαλογή και τη μεταφορά του τοπικού συνεργάτη πριν από τη διανομή. Τα `It is currently in transit to the next facility`, `The package is being transferred to the next facility` και `Shipment arrived to sorting centre / Shipment in the sorting centre` είναι επίσης μετακινήσεις συνεργάτη, και το Peek εμφανίζει και τις επτά ως «In transit». Σε δέμα προς το Ηνωμένο Βασίλειο, οι γραμμές της [InPost](carrier:inpost) μπορεί να εμφανιστούν πριν από το σκανάρισμα στη Σεντζέν: `Your parcel is on its way to InPost. Please check back for updates` («Label created») και `We have your parcel. Please check back for updates` («Received by carrier»). Η σημείωση της InPost `We'll apply your My Account preferences to this parcel delivery` και το `Consignee Contacted` (τηλεφώνημα διανομέα που καταγράφεται το ίδιο λεπτό με αποτυχημένη προσπάθεια) διατηρούνται στο ιστορικό αλλά δεν αλλάζουν στάδιο.

**Ώρες.** Κάθε σκανάρισμα φέρει τη διαφορά του από UTC. Το Peek τα διαβάζει έτσι και τα προβάλλει στη ζώνη ώρας της συσκευής σας, με τα νεότερα πρώτα.

## Ποιος παραδίδει τα δέματα της WanbExpress

Τα σκαναρίσματα της WanbExpress ακολουθούν το δέμα από την Κίνα μέσω τελωνείου έως έναν τοπικό μεταφορέα, ο οποίος εκτελεί την παράδοση. Το σύστημα της WanbExpress μπορεί να αναφέρει αυτόν τον μεταφορέα, τον κωδικό του και φωτογραφία παράδοσης. Το Peek παραλείπει τη φωτογραφία· όταν αναγνωρίζει σε ποιον ανήκει ο αριθμός του τοπικού μεταφορέα, εμφανίζει «Delivery with …» και τον παρακολουθεί, αλλιώς ελέγξτε στο σύστημα της WanbExpress ή ρωτήστε τον πωλητή.

**Τι δείχνει ο κώδικας του Peek.** Σε δέμα προς τη Γερμανία εμφανίζεται η iMile, ένα σκανάρισμα αναφέρει το δίκτυο της Poste Italiane στην Ιταλία, και προς το Ηνωμένο Βασίλειο εμφανίζονται οι γραμμές της InPost.

**Προσπάθειες παράδοσης και παραλαβή.** Όποιος παραδίδει, ισχύουν οι δικοί του κανόνες (πόσες προσπάθειες γίνονται, αν αφήνεται ειδοποίηση, πού και πόσο μένει το δέμα). Το Peek δεν εντόπισε κανόνες της WanbExpress γι' αυτά. Μόλις μάθετε τον τοπικό μεταφορέα και τον αριθμό του, ελέγξτε το δικό του σύστημα και ρωτήστε τον πώς να κανονίσετε νέα παράδοση.

## Το δέμα WanbExpress δεν φτάνει ή η παρακολούθηση έχει κολλήσει

**Ο αριθμός δεν βρέθηκε.** Αν το σύστημα της WanbExpress δεν γνωρίζει τον αριθμό, το Peek δείχνει «Waiting for WanbExpress» και συνεχίζει τους ελέγχους. Αν ούτε δύο ημέρες αργότερα δεν τον γνωρίζει ούτε η WanbExpress ούτε κάποια υπηρεσία παρακολούθησης, το Peek εμφανίζει «After two days, the carrier still doesn’t know this number. Check it, choose another carrier or delete the parcel.» Ελέγξτε χαρακτήρα προς χαρακτήρα: `WNB`, δύο γράμματα, δέκα ψηφία, `YQ`. Αν δεν αλλάξει τίποτα μετά από μερικές ημέρες, ρωτήστε τον πωλητή αν στάλθηκε.

**Κολλημένο σε `Shipping Order Created.` ή `Parcel Data Received`.** Το κατάστημα καταχώρισε την αποστολή, αλλά η WanbExpress δεν έχει σκανάρει ακόμη το δέμα. Ρωτήστε το κατάστημα πότε το παρέδωσε.

**Κολλημένο μετά το `Parcel arrives at Airport, Parcel Arrival`.** Το δέμα πιθανότατα περιμένει εκτελωνισμό εισαγωγής. Για την ΕΕ, αγαθά που αγοράζονται online εκτός ΕΕ επιβαρύνονται από την 1η Ιουλίου 2026 με δασμό 3 € ανά είδος προϊόντος σε δέματα αξίας έως 150 €, και από την 1η Νοεμβρίου 2026 με ενωσιακό τέλος διαχείρισης 2 € ανά είδος προϊόντος ανεξαρτήτως αξίας. Αν σας ζητηθούν χρεώσεις εισαγωγής, δείτε [δέματα στο τελωνείο](guide:customs) (στα Αγγλικά)· αν παραμείνει εκεί μέρες, ενημερώστε τον πωλητή. Δείτε επίσης [γιατί σταματά να ενημερώνεται η παρακολούθηση](guide:tracking-not-updating) (στα Αγγλικά).

**Τίποτα μετά το `Custom clearance completed`.** Ο τοπικός μεταφορέας έχει ή πρόκειται να λάβει το δέμα και το δικό του σύστημα μπορεί να είναι πιο μπροστά. Βρείτε το όνομα και τον κωδικό του στη σελίδα της WanbExpress ή ρωτήστε τον πωλητή.

**Αποτυχημένη προσπάθεια παράδοσης.** Μετά από γραμμή όπως `<Route not completed>`, ο τοπικός μεταφορέας αποφασίζει πότε θα ξαναπροσπαθήσει και πού φυλάσσεται το δέμα: επικοινωνήστε με εκείνον, όχι με τη WanbExpress.

**Παραδόθηκε, αλλά δεν ήρθε τίποτα.** Αναζητήστε φωτογραφία παράδοσης στο σύστημα της WanbExpress και έπειτα απευθυνθείτε στον τοπικό μεταφορέα και στον πωλητή. Περισσότερα στο [παραδόθηκε αλλά δεν παρελήφθη](guide:delivered-not-received) (στα Αγγλικά).

**Χαμένο ή επιστραμμένο.** Την αποστολή έκλεισε ο πωλητής, επομένως ζητήστε επιστροφή χρημάτων ή νέο δέμα από τον πωλητή ή την πλατφόρμα αγοράς.

## Επικοινωνία και εξυπηρέτηση πελατών WanbExpress

Το Peek δεν μπόρεσε να ελέγξει τον ιστότοπο της WanbExpress, οπότε αυτή η σελίδα δεν αναφέρει στοιχεία επικοινωνίας της. Για την παραγγελία σας απευθυνθείτε πρώτα στον πωλητή (και στην πλατφόρμα αν δεν απαντά), και για την ίδια την παράδοση στον τοπικό μεταφορέα.

## Παρακολούθηση δέματος WanbExpress με το Peek

Το [Peek](/) αναγνωρίζει τον κωδικό WanbExpress από τη μορφή του (`WNB`, δύο γράμματα, δέκα ψηφία και `YQ`, μοναδική στον κατάλογό του) και ρωτά το σύστημα της WanbExpress μόνο με αυτόν: δεν απαιτείται λογαριασμός, ταχυδρομικός κώδικας ή τηλέφωνο.

Επειδή το σύστημα της WanbExpress απαιτεί έλεγχο Google reCAPTCHA, το Peek το ελέγχει μέσω προγράμματος περιήγησης. Όταν δεν απαντά, στρέφεται σε υπηρεσίες παρακολούθησης και δείχνει το δικό τους ιστορικό, το οποίο μπορεί να καθυστερεί σε σχέση με της WanbExpress.

Από το σύστημα της WanbExpress το Peek εμφανίζει την κατάσταση, κάθε σκανάρισμα με την τοποθεσία του και τις χώρες προέλευσης και προορισμού. Όταν η εγγραφή σημειώνει το δέμα σε μεταφορά, σε διανομή ή παραδομένο, το Peek ακολουθεί αυτή την ένδειξη ακόμη κι αν το τελευταίο σκανάρισμα λέει λιγότερα. Δεν εμφανίζει εκτιμώμενη ημερομηνία παράδοσης.

Το Peek παραλείπει τις φωτογραφίες παράδοσης και τη διεύθυνση του παραλήπτη. Όταν η WanbExpress δίνει τον αριθμό του τοπικού μεταφορέα και το Peek τον αναγνωρίζει, εμφανίζει «Delivery with …» και συνεχίζει εκεί· διαφορετικά προσθέστε τον αριθμό του τοπικού μεταφορέα ξεχωριστά στο Peek (υποστηρίζει πάνω από 4.500 μεταφορείς).

Όσο έχετε ανοιχτό το δέμα, ή την ημέρα παράδοσης με ενεργές ειδοποιήσεις και όσο απαντά η WanbExpress, το Peek ελέγχει έως κάθε 10 λεπτά και έως κάθε 2 λεπτά από τις 6:00 π.μ. ώρας WanbExpress την ημέρα διανομής (κάθε 5 λεπτά όταν ανοίγει πρόγραμμα περιήγησης)· διαφορετικά κάθε ώρα. Όταν απαντούν υπηρεσίες παρακολούθησης, ελέγχει κάθε ώρα, κάθε 3 ώρες μετά από μία ήσυχη ημέρα και έως κάθε 15 λεπτά την ημέρα διανομής, όσο έχετε ανοιχτό το δέμα ή ενεργές ειδοποιήσεις. Μετά από δύο ημέρες χωρίς νέα (12 ώρες σε «Label created» ή «Received by carrier»), ελέγχει κάθε ώρα. Ειδοποιεί για κάθε σκανάρισμα, σημαντικά στάδια ή «Delivery only»: τα «Important steps» καλύπτουν τη διανομή, την αποτυχημένη προσπάθεια και την παράδοση, όχι όμως τις γραμμές τελωνείου της WanbExpress που το Peek κατατάσσει ως «In transit». Δεν μπορεί να επικοινωνήσει με τη WanbExpress ή τον πωλητή εκ μέρους σας.

## Συχνές ερωτήσεις για την παρακολούθηση WanbExpress

### Τι είναι η WanbExpress (Wanb Express / WANB);

Κινεζικός διασυνοριακός μεταφορέας, γνωστός και ως Wanb Express, WanBang Express ή WANB. Στον κώδικα του Peek, τα σκαναρίσματά του ξεκινούν από κέντρο διαλογής στη Σεντζέν, καλύπτουν την πτήση και το τελωνείο και συνεχίζουν με τον τοπικό μεταφορέα στη χώρα σας.

### Πού μπορώ να παρακολουθήσω ένα δέμα WanbExpress WNB…YQ;

Επικολλήστε ολόκληρο τον κωδικό `WNB…YQ` των 17 χαρακτήρων (`WNB`, δύο γράμματα, δέκα ψηφία, `YQ`) στο πεδίο παραπάνω ή στο tracking.wanbexpress.com, το οποίο χρησιμοποιεί Google reCAPTCHA και μπορεί να δείξει το όνομα και τον κωδικό του τοπικού μεταφορέα.

### Τι σημαίνει «Customs declaration completed» στη WanbExpress;

Ότι ολοκληρώθηκε η τελωνειακή διασάφηση του δέματος — δεν είναι ακόμη ο τελικός εκτελωνισμός εισαγωγής, ο οποίος εμφανίζεται ως `Custom clearance completed`. Το Peek κατατάσσει και τις δύο γραμμές ως «In transit».

### Ποιος παραδίδει δέματα WanbExpress στη Γερμανία, το Ηνωμένο Βασίλειο, την Ιταλία ή τις ΗΠΑ;

Ένας τοπικός μεταφορέας που η σελίδα της WanbExpress μπορεί να αναφέρει μαζί με δεύτερο κωδικό. Ο κώδικας του Peek δείχνει την iMile στη Γερμανία, την InPost στο Ηνωμένο Βασίλειο, το δίκτυο της Poste Italiane στην Ιταλία και τοπικά κέντρα στις ΗΠΑ· ελέγξτε το σύστημα της WanbExpress ή ρωτήστε τον πωλητή.

### Με ποιον να επικοινωνήσω για κολλημένο ή χαμένο δέμα WanbExpress;

Απευθυνθείτε πρώτα στον πωλητή ή την πλατφόρμα αγοράς, καθώς το κατάστημα έκλεισε τη μεταφορά με τη WanbExpress· για θέματα τελικής παράδοσης επικοινωνήστε με τον τοπικό μεταφορέα μόλις έχετε τον κωδικό του.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – names, WNB number shape, the tracker, its reCAPTCHA check and the link that opens one parcel, scan wordings and their stages, iMile's relayed scans on a parcel to Germany and its next-run line, the US last-mile partner's and other partners' relayed moves, InPost's relayed lines on a parcel to the UK and its account-settings note that moves nothing, the scan naming Poste Italiane's network, times with UTC offsets, what the tracker shows (delivering carrier, its number, delivery photos, recipient's address), what Peek keeps and leaves out, and the tracking services it turns to
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – €3 customs duty per kind of product up to €150 since 1 July 2026, €2 Union handling fee since 1 November 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – €2 per kind of product like the €3 duty, whatever the value
:::
