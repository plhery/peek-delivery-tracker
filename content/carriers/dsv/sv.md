---
title: DSV-spårning: sändningsnummer, status och DB Schenker
description: Spåra en DSV- eller tidigare DB Schenker-sändning i Sverige och Europa: nummerformat, statuskoder i myDSV, leverans, reklamation via eClaims och kontakt.
slug: dsv-sparning
published: 2026-11-24
updated: 2026-11-24
---

DSV är en dansk transport- och logistikkoncern som fraktar gods på väg, flyg och sjö, och 2025 köpte DSV Schenker (DB Schenker) av Deutsche Bahn. På [DSV Sveriges sida för myDSV](https://www.dsv.com/sv-se/digitala-losningar/boka-och-spara) och på [dsv.com](https://www.dsv.com/en/digital-solutions/book-and-track) finns två spårningsfält: ”Spåra sändningar bokade med DSV” öppnar DSV:s spårning i myDSV och ”Spåra sändningar bokade i Schenkers system” den tidigare DB Schenker-spårningen; på den engelska sidan heter de ”Track DSV shipment” och ”Track Schenker shipment”. Inget konto krävs. Du får numret av avsändaren eller butiken och kan klistra in det direkt i fältet ovan.

## DSV-spårningsnummer: format och var du hittar det

Företaget som bokade frakten har numret: titta i butikens leveransmejl, på ordersidan eller fråga avsändaren. Behöver du hjälp att hitta det, se [hur du hittar ditt spårningsnummer](guide:find-tracking-number).

Under tiden som DSV slår samman Schenkers system finns två spårningar kvar: en sändning som bokats med Schenker kan bara finnas i Schenker-spårningen. DSV Sverige skriver att sändningar till ombud och paketboxar för närvarande kommer från f.d. Schenkers system, och att orderbekräftelsen visar vem som levererar. Visar den ena spårningen inget resultat, testa den andra. Alla exempel nedan har påhittade siffror:

| Nummer (påhittat) | Vad det är |
| --- | --- |
| `DEKLM482917365` | 14 tecken: en europeisk landskod som `DE`, `SE`, `DK` eller `PL`, tre bokstäver och nio siffror. Ett tidigare DB Schenker-vägsändningsnummer och formatet Peek kopplar till DSV. |
| `KLM4829173` | Tre bokstäver och sju eller åtta siffror: ett äldre DB Schenker-sändningsnummer för vägfrakt. Peek namnger inte DSV utifrån enbart formatet. |
| `094827361549283711` | 18 siffror (20 med `00` framför): kollinumret på streckkoden för ett paket i en tidigare DB Schenker-vägsändning. |
| Bokstäver och siffror | Ett sändnings-, boknings- eller house-bill-nummer i myDSV. Peek frågar myDSV för alla nummer på 5–40 bokstäver och siffror som innehåller minst en siffra. |
| `mydsv.com/track-shipment?query=DEKLM482917365` | En direktlänk till spårningen i myDSV. |

Kund- eller orderreferenser samt flygbolagets master air waybill kan fungera i myDSV:s sökfält, men Peek spårar inte referenser utan kräver DSV:s sändnings-, boknings- eller house-bill-nummer. Peek kan inte heller välja en enskild sändning om samma nummer träffar flera sändningar.

## Vad DSV:s och DB Schenkers statusar betyder

myDSV loggar varje skanning från bokning till leveransbevis; den publika vyn visar översiktliga steg medan den inloggade vyn ger detaljer. De vanligaste myDSV-stegen som Peek läser:

| DSV visar | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Booking` | DSV har mottagit bokningen (daterad utan klockslag). | "Label created" |
| `Creation` | Sändningen har skapats i DSV:s vägsystem. | "Label created" |
| `Actual pickup` | DSV har hämtat godset hos avsändaren. | "Received by carrier" |
| `Cargo received` | Godset har tagits emot av DSV på terminal eller vid upphämtning. | "Received by carrier" |
| `Actual Departure`, `Actual departure - Origin Port` | Har lämnat avgångshamn eller flygplats på huvudsträckan. | "In transit" |
| `Actual arrival`, `Actual arrival- Destination Port` | Har anlänt till ankomsthamn eller flygplats. | "In transit" |
| `Handover responsibilty of cargo`, `Handover of Cargo` | DSV har överlämnat godset vid slutet av sitt uppdrag, vilket avslutar sändningen (den första behåller DSV:s stavning). | "Delivered" |
| `Actual delivery` | Levererad. | "Delivered" |

Rader som `Estimated Departure`, `Estimated arrival`, `Estimated pickup`, `Estimated delivery`, `Requested pickup` och `Requested delivery` är planerade tider, inte faktiska skanningar: Peek utelämnar dem ur historiken men visar `Estimated delivery` som förväntad leveransdag (eller intervall) tills leveransen är klar.

Den tidigare DB Schenker-spårningen för vägsändningar använder egna engelska rader:

| DB Schenker-spårningen visar | Vad det betyder för dig | Peek visar |
| --- | --- | --- |
| `Booked` | Avsändaren har bokat transporten. | "Label created" |
| `Out for collection` | En chaufför är på väg för upphämtning hos avsändaren. | "Label created" |
| `Collected` | Upphämtad hos avsändaren. | "Received by carrier" |
| `Delivered to terminal by shipper` | Avsändaren har lämnat in godset på terminal. | "Received by carrier" |
| `Arrived`, `Departed` | Har ankommit till eller lämnat en terminal. | "In transit" |
| `Customs clearance initiated` | Tullklarering har påbörjats. | "At customs" |
| `Customs clearance finalised` | Tullklareringen är klar och sändningen fortsätter. | "In transit" |
| `Out for Delivery` | Lastad på distributionsbilen för leverans. | "Out for delivery" |
| `Not delivered` | Leveransförsöket misslyckades; en orsak kan anges efteråt. | "Delivery attempted" |
| `To consignee's disposal`, `Ready for pick up` | Väntar på upphämtning. | "Ready for pickup" |
| `Delivered` | Levererad. | "Delivered" |
| `Picked up by consignee`, `Picked up at agent` | Hämtad av mottagaren eller hos ombud. | "Delivered" |
| `ePOD available` | Elektroniskt leveransbevis finns tillgängligt. | "Delivered" |

Under resan förekommer även `Linehaul departed`, `Linehaul arrived`, `Terminal inventory`, `Vessel Loaded`, `Vessel Unloaded`, `Train Loaded`, `Train Unloaded`, `Vessel/Train Loaded` och `Vessel/Train Unloaded` — alla visas som "In transit" i Peek. `Not Collected` (misslyckad upphämtning) och `Not loaded` (missade planerad lastning) visas som "Needs attention". Om spårningen lägger till en förklaring bredvid ett steg tar Peek med den. Mer om stadier finns i [spårningsstatusar](guide:tracking-statuses).

## Hur DSV levererar i Sverige och Europa

**Pallgods och styckegods.** DSV kallar sitt europeiska styckegodsnätverk för Europas största, med över 36 länder, fler än 400 terminaler och sändningar upp till 2 500 kg. En pall eller skrymmande beställning kommer därför ofta med lastbil från närmaste DSV-terminal.

**E-handelspaket.** DSV:s tjänst Parcel E-commerce samlar in paket från e-handlare, konsoliderar över gränser och låter lokala sista-milen-nätverk leverera. Om butikens leveransmejl anger en lokal transportör eller ett andra nummer bör du spåra även det, eftersom DSV:s egen spårning inte anger någon efterföljande transportör till Peek.

**Avisering och leveransbevis.** Inloggade användare i myDSV kan aktivera aviseringar (till exempel vid upphämtning eller tullklarering), se beräknad ankomsttid för vägfrakt och ladda ner leveransbevis när sändningen är klar. Om du inte själv bokade DSV, fråga avsändaren. DSV:s hjälpsidor anger inte hur många leveransförsök som görs eller hur länge en terminal lagrar gods — fråga det DSV-kontor som hanterar leveransen.

**Tull i EU och Sverige.** I de flesta länder där DSV har kontor fungerar bolaget som licensierat tullombud och arbetar i övriga länder med agenter. För hjälp med en tullklarering hänvisar DSV till sitt kontor i det landet. För varor som köps på nätet från länder utanför EU gäller sedan 1 juli 2026 en EU-tullavgift på 3 euro per varuslag för försändelser upp till 150 euro, och sedan 1 november 2026 en EU-hanteringsavgift på 2 euro per varuslag oavsett värde. Se [paket som fastnat i tullen](guide:customs).

## När DSV-sändningen dröjer, saknas eller är skadad

**Inga nya uppdateringar.** Om den ena spårningen stannar, testa det andra fältet på dsv.com eftersom en Schenker-bokad sändning inte alltid har flyttats till myDSV ännu. Kontakta därefter avsändaren som bokade frakten. Läs mer under [spårning som inte uppdateras](guide:tracking-not-updating).

**Skadat eller saknat gods.** Vid nätköp vänder du dig först till butiken som är DSV:s avtalspart. DSV Sverige skriver att saknat gods först ska efterlysas hos DSV:s kundtjänst och att du kan reklamera om det inte har hittats inom 10 dagar. En reklamation för en DSV-sändning registreras i myDSV med kopia på handelsfakturan, foton på emballage och gods och en kopia av fraktsedeln; f.d. Schenker-sändningar har en egen rutin. DSV:s portal [eClaims](https://www.dsv.com/en/support/self-services/claims) tar emot skade- och förlustanmälningar med underlag, visar ärendestatus och kopplar dig till lokala skadereglerare. Portalen anger ingen tidsfrist, så anmäl så snart som möjligt. Se [skadat paket](guide:damaged-parcel) och [försvunnet paket](guide:lost-parcel).

**Bedrägerivarning.** DSV varnar på sin juridiska informationssida för att obehöriga företag och personer ibland utnyttjar DSV:s namn för att sälja frakttjänster. Kontrollera alltid misstänkta kontakter via ditt lokala DSV-kontor, och läs [bedrägeri-sms om leveranser](guide:delivery-scam-texts) om du får oväntade betalningskrav via sms.

## DSV kontakt och kundservice i Sverige

På DSV:s webbplats:

- **Kundsupport för mottagare:** DSV Sverige ber dig först kontakta avsändaren. Vid brådskande ärenden kan du ringa vardagar, privatpersoner 08.00–17.00 och företag 07.00–17.00: `0771-404 046` för vägtransport och `031-7038 000` för sjö- och flygfrakt, enligt [DSV:s sida för dig som väntar på en leverans](https://www.dsv.com/sv-se/support/kundservice/kundservice-mottagare).
- **Kontaktformulär i Sverige:** via [DSV Sveriges hjälpsida](https://www.dsv.com/sv-se/support/hjalp-och-kontakt) samt den internationella [Help and contact](https://www.dsv.com/en/support/help-and-contact).
- **Lokala kontor:** DSV finns i över 90 länder; sök kontor i Sverige och utomlands via [Hitta ditt lokala DSV-kontor](https://www.dsv.com/sv-se/support/hitta-dsv).
- **Reklamationer:** via eClaims på [DSV:s reklamationssida](https://www.dsv.com/en/support/self-services/claims); i Sverige i myDSV, enligt [DSV Sveriges reklamationssida](https://www.dsv.com/sv-se/digitala-losningar/mydsv/reklamation-via-mydsv).
- **Huvudkontor (DSV A/S):** Hedehusene, Danmark, `+45 43 20 30 40` (från DSV:s juridiska meddelande — inte en kundtjänstlinje för spårning).


## Spåra en DSV-sändning med Peek

Klistra in ett 14-teckensnummer som `DEKLM482917365` i [Peek](/) så frågar Peek DSV direkt när du lägger till paketet och visar "DSV has this parcel." när DSV känner igen det. Har DSV ännu inga skanningar visas paketet som "Probably DSV" med "Waiting for DSV’s first scan" medan Peek fortsätter fråga; efter en vecka ber Peek dig kontrollera numret eller välja en annan transportör.

För övriga nummerformat kan Peek inte peka ut DSV på formen ensam, men klistra in numret ändå — Peek följer fler än 4 500 transportörer. För att välja DSV själv trycker du på "Change" vid tillägg (eller "Change carrier" på ett tillagt paket) och söker på DSV eller Schenker. Direktlänkar från `mydsv.com` samt länkar från Schenker-spårningen på `dsv.com` med `refNumber=` läggs automatiskt under DSV.

Peek frågar myDSV först. Om myDSV inte känner igen ett tidigare DB Schenker-vägnummer eller kollinummer provar Peek Schenker-spårningen (som Peek läser för vägfrakt; för flyg- eller sjöfrakt som bara finns där använder du Schenker-fältet på dsv.com).

Peek visar status, varje händelse i DSV:s ordalydelse med ort och land samt beräknad leveransdag. Eftersom DSV skickar lokal tid utan tidszon räknar Peek ut tidszonen från skanningsplatsen (stad, hamn eller flygplatskod) eller omgivande händelser, annars dansk tid (Köpenhamn), vilket i enstaka fall kan avvika. Mottagarens namn, när DSV anger det, visas under "Recipient" på din egen paketsida och mottagarorten kan markera slutmålet på din egen karta, men inget av dem visas på delade länkar. Peek utelämnar tjänstenamn, avsändarort, gatuadress, postnummer, förloppsindikator samt från Schenker-spårningen avsändare, referenser, varubeskrivning och namnet på den som kvitterade.

Om [Early Bird](carrier:early-bird) lämnar över ditt paket i Sverige till DSV eller DB Schenker visar Peek DSV som levererande transportör och växlar över till DSV så snart DSV har minst lika färska skanningar.

Medan sändningen är öppen i Peek, eller på leveransdagen med aviseringar aktiverade, kollar Peek upp till var 10:e minut och varannan minut från kl. 06.00 lokal tid den dag DSV skannar paketet som "Out for delivery", så länge DSV:s egen spårning svarar; annars varje timme. Om bara spårningstjänster svarar sker kontrollen varje timme, var 3:e timme efter ett dygn utan nyheter och upp till var 15:e minut den dag paketet skannas som "Out for delivery", medan paketet är öppet eller aviseringar är på. Efter två dagar utan nyheter (eller 12 timmar i läget "Label created" eller "Received by carrier") kollar Peek en gång i timmen hur du än följer det, och var 6:e timme efter sju dagar utan nyheter i "Label created". När senaste skanningen är fyra dagar gammal i "Label created", "Received by carrier" eller "In transit" visar Peek dagen (till exempel "No tracking update since Mon 12 Oct"), meddelandet "Parcels often go quiet on long flights, at customs or over a weekend. Peek keeps asking DSV." och länken "Get help from DSV". Efter tre veckor utan ny skanning visas "Nothing new for over three weeks. If it still hasn’t arrived, ask the seller. Peek keeps asking DSV."

Du kan välja aviseringar för "Every scan", "Important steps" eller "Delivery only". Peek kan inte kontakta DSV eller avsändaren åt dig, boka utkörning eller lämna in en reklamation.

## Vanliga frågor om DSV-spårning

### Är DSV samma företag som DB Schenker?

Ja. DSV, grundat 1976, slutförde köpet av Schenker från Deutsche Bahn den 30 april 2025, och `dbschenker.com` har slagits samman med `dsv.com`. Tidigare Schenker-sändningar har tills vidare kvar ett eget spårningsfält.

### Hur spårar jag en DB Schenker-sändning nu?

Ange numret i fältet för sändningar bokade i Schenkers system på dsv.com eller klistra in det i rutan ovan; har numret inte 14 tecken som börjar med en landskod väljer du DSV manuellt. Peek kollar först myDSV och därefter Schenker-spårningen för vägfrakt.

### Vad betyder `Handover of Cargo` hos DSV?

Det betyder att DSV har lämnat över godset vid slutet av sitt uppdrag, och Peek visar "Delivered". Om du inte har fått varan, fråga avsändaren var DSV lämnade över godset och se [levererat men saknas](guide:delivered-not-received).

### Har DSV ett kundtjänstnummer för spårning?

Ja, i Sverige, för brådskande ärenden: vardagar `0771-404 046` för vägtransport och `031-7038 000` för sjö- och flygfrakt. DSV ber dig ändå först fråga avsändaren. Du kan också använda kontaktformuläret eller söka ditt lokala kontor på [dsv.com](https://www.dsv.com/sv-se/support/hitta-dsv). Numret `+45 43 20 30 40` i DSV A/S:s juridiska meddelande i Danmark är ingen spårningslinje.

:::sources
- [DSV Sverige: Boka och spåra med myDSV](https://www.dsv.com/sv-se/digitala-losningar/boka-och-spara) – svensk spårningsingång för myDSV
- [DSV Sverige: Hjälp och kontakt](https://www.dsv.com/sv-se/support/hjalp-och-kontakt) – kontaktformulär och kundservice i Sverige
- [DSV Sverige: Kundsupport – Ta emot](https://www.dsv.com/sv-se/support/kundservice/kundservice-mottagare) – kontakta avsändaren först, ombud och paketboxar via f.d. Schenkers system, telefon vid brådskande ärenden
- [DSV Sverige: Reklamation](https://www.dsv.com/sv-se/digitala-losningar/mydsv/reklamation-via-mydsv) – saknat gods efter 10 dagar, reklamation i myDSV, underlag, f.d. Schenkers rutin
- [DSV Sverige: Hitta ditt lokala DSV-kontor](https://www.dsv.com/sv-se/support/hitta-dsv) – kontorssökning i Sverige och över 90 länder
- [DSV: Book and track shipments with myDSV](https://www.dsv.com/en/digital-solutions/book-and-track) – spårning utan konto, de två fälten och sammanslagningen av dbschenker.com
- [DSV: myDSV](https://www.dsv.com/en/digital-solutions/mydsv) – publik och privat vy, aviseringar och leveransbevis
- [DSV: Self-services](https://www.dsv.com/en/support/self-services) – DSV- och Schenker-spårning, eClaims
- [DSV: Frequently asked questions](https://www.dsv.com/en/support/faq) – kontaktformulär och myDSV-versioner
- [DSV: Help and contact](https://www.dsv.com/en/support/help-and-contact) – över 90 länder och kontorsfinder
- [DSV: Claims](https://www.dsv.com/en/support/self-services/claims) – reklamationsportal eClaims
- [DSV: Groupage transport](https://www.dsv.com/en/our-solutions/modes-of-transport/road-transport/groupage) – 36+ länder, 400+ terminaler, upp till 2 500 kg
- [DSV: E-commerce delivery solutions](https://www.dsv.com/en/our-solutions/contract-logistics/ecommerce-solutions/last-mile-delivery) – gränsöverskridande konsolidering och lokala nätverk
- [DSV: Customs clearance](https://www.dsv.com/en/our-solutions/modes-of-transport/value-added-services/customs-clearance) – tullombud och agenter
- [DSV: History](https://www.dsv.com/en/about-dsv/history) – grundat 1976, väg/flyg/sjö och köpet av Schenker 2025
- [DSV: DSV completes acquisition of Schenker](https://www.dsv.com/en/about-dsv/press/news/dsv-completes-acquisition-of-schenker-2628130) – slutfört 30 april 2025
- [DSV: Legal notice](https://www.dsv.com/en/about-dsv/legal-notice) – DSV A/S Danmark, telefon och varning mot missbruk av DSV:s namn
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – 3 € tullavgift sedan 1 juli 2026 och 2 € EU-hanteringsavgift sedan 1 november 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € per varuslag oavsett värde
- [Universal Parcel Scraper: DSV](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dsv) – nummerformat, länkar, båda spårningarna, statusar, lokala klockor, Early Bird-överlämning och Peek-fält
:::
