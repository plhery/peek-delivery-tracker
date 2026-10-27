---
title: Spåra Posti-paket: försändelsekod, automat och kundtjänst
description: Spåra ditt Posti-paket: så ser försändelsekoden ut, det här betyder statusarna, så hämtar du paketet ur automaten och så når du Postis kundtjänst.
slug: posti-spara-paket
published: 2026-10-26
updated: 2026-10-27
---

Posti delar ut post och paket i Finland. Du spårar paketet i [Postis uppföljning](https://www.posti.fi/sv/uppfoljning) eller i OmaPosti-appen med försändelsekoden, eller så klistrar du in koden i fältet ovanför. Koden får du av avsändaren, oftast webbutiken.

## Postis försändelsekod

I Postis uppföljning heter numret försändelsekod. Postis egna paket har en kod som börjar på `JJFI`. Det här är formerna du oftast ser:

| Kod (påhittad) | Vad det är |
| --- | --- |
| `JJFI00000000000000000` | Ett Posti-paket: `JJFI` och 17 siffror, 21 tecken sammanlagt. |
| `RR000000005FI` | Två bokstäver, nio siffror och `FI`: en spårbar postförsändelse som lämnats in i Finland, i det internationella formatet. |
| `RR000000005CN` | Ett paket från utlandet behåller koden från avsändarlandet. Den här slutar på `CN` och lämnades alltså in hos [China Post](carrier:china-post). Tills paketet har kommit till Finland står det `Paketet är utomlands` i Postis uppföljning. |
| `00064000000000000002` | En SSCC-streckkod på 20 siffror. `64` efter de tre första siffrorna är Finlands prefix. |

**OmaPosti.** OmaPosti visar försändelser utifrån telefonnumret. Har avsändaren gett Posti ditt nummer dyker paketet upp av sig självt, annars lägger du till det själv. I Postis uppföljning kan du bara söka med den exakta försändelsekoden, inte med namn eller adress.

**Koder du inte kan spåra med.** Öppningskoden till automaten (sex eller nio tecken) och uthämtningskoden som du visar vid disken använder du bara när du hämtar paketet. En kod som börjar på `JJEE` hör till [Omniva](carrier:omniva), Estlands post, och en som slutar på `SE` till [PostNord](carrier:postnord) i Sverige.

## Statusar i Postis uppföljning

Postis uppföljning på svenska visar överst en rubrik för det steg paketet är i, ofta med en rad under som förklarar steget. Det här är de rubriker du oftast ser, ungefär i den ordning de kommer:

| Posti skriver | Vad det betyder för dig |
| --- | --- |
| `Vi väntar på försändelsen` | Avsändaren har meddelat Posti om paketet, men Posti har inte fått det ännu. |
| `Vi förbereder en paketets resa.` | Posti har paketet. Raden under säger `Vi har ditt paket och det är nu på väg till dig.` |
| `Paketet är utomlands` | Paketet har lämnats in utomlands och har inte kommit till Finland ännu. Det kan gå länge mellan uppdateringarna. Verkar något ovanligt ber Posti dig kontakta avsändaren. |
| `Anlänt till landet` | Paketet har kommit till Finland och väntar på tullbehandling. Ska du förtulla det själv står det `Gör förtullningen på tulli.fi`. |
| `Paketet är på väg` | Paketet är på väg mot uthämtningsstället eller till din dörr. |
| `Ditt paket anländer snart!` | Paketet är på sista biten av resan. |
| `Uthämtningsstället ändras.` | Paketet går till ett annat ställe än det du valde, till exempel för att automaten saknar en box i rätt storlek, för att paketet är för stort för automaten eller för att det är ett postförskottspaket. |
| `Hemleveransen misslyckades.` | Posti kunde inte lämna paketet hos dig och har tagit det till ett uthämtningsställe. |
| `Framme vid uthämtningsstället!` | Paketet väntar på dig. Under rubriken står `Paketet ska hämtas senast` och datumet. |
| `Paketet har levererats` | Du har hämtat paketet eller fått det till dörren. |
| `Sista dagen för förtullning närmar sig.` | Förtulla snart, annars går paketet tillbaka till avsändaren. |

**På väg tillbaka.** Står det `Paketet är på väg` med raden `Vi transporterar paketet tillbaka till avsändaren.` går paketet i retur. `Paketet har levererats` med raden `Paketet har returnerats till avsändaren.` betyder att avsändaren har fått det tillbaka.

**Ingen träff.** Står det `Ingen försändelse hittades` kan det bero på ett skrivfel, på mellanslag i koden eller på att försändelsen inte har registrerats eller lämnats in för transport ännu. Uppföljningen visar inte heller försändelser som skickades för mer än 2,5 månader sedan. Sök med en kod i taget. Händer inget på flera dagar? Läs om [spårning som inte uppdateras](guide:tracking-not-updating) (på engelska).

**Beräknad leveranstid.** I uppföljningen ser du när paketet beräknas komma fram, vart det ska levereras och var i sorteringen och transporten det är. För ett paket som ska förtullas kommer uppskattningen först efter förtullningen.

## Automat, serviceställe eller hemleverans

**Ankomstavi.** När paketet har kommit till en automat eller ett serviceställe får du en ankomstavi i OmaPosti, per sms eller per e-post. Hämta paketet inom 5 dagar. Vissa försändelser förvaras längre, till exempel många paket från utlandet. Den sista uthämtningsdagen står i ankomstavin.

**Mer tid.** Går det att förlänga hämtningstiden ser du `Pidennä noutoaikaa` (Förläng hämtningstiden) bland paketets uppgifter i OmaPosti-appen. Du kan förlänga med upp till 14 dagar, räknat från den ursprungliga sista uthämtningsdagen. Tjänsten kostar 1,90 € enligt Postis svenska sida, men 1,90 € per dag enligt den finska.

**I automaten.** Öppningskoden har nio tecken i automater utomhus och sex i övriga automater med knappsats. På knappsatsen trycker du först C, sedan koden och till sist OK. Efter 3 felaktiga försök låses knappsatsen i 5 minuter. På en pekskärm väljer du att hämta paket, skriver in koden och väljer att öppna boxen. Öppnas luckan inte, kontrollera att namnet högst upp på automaten är detsamma som i ankomstavin.

**På servicestället.** Ett paket på servicestället har oftast en uthämtningskod: visa den i telefonen vid disken, så behövs inget identitetsbevis. Rekommenderade försändelser har ingen uthämtningskod. Dem hämtar du med försändelsekoden och ett identitetsbevis, till exempel körkort, pass eller polisens identitetskort. Ett uppehållstillstånd duger inte.

**Någon annan hämtar.** Den som har boxkoden kan hämta paketet ur automaten åt dig. På servicestället räcker uthämtningskoden, utan identitetsbevis eller fullmakt. Fullmakt behövs för rekommenderade brev och paket, brev med mottagningsbevis och assurerade försändelser. En försändelse som ska överlämnas personligen kan bara du själv hämta.

**Inte till din automat?** Paketet kan ha gått till ett serviceställe för att det är för stort för automaten, ska överlämnas ansikte mot ansikte eller har fått förlängd förvaringstid, eller för att telefonnumret i beställningen var fel. Farliga ämnen, till exempel nagellack, förs inte till en automat under marknivå.

**Hemleverans.** Småpaket till dörren är för paket på högst 38 x 38 x 28 cm och 4 kg. Får paketet inte plats i postlådan och du inte är hemma lämnas det vid dörren. Leveranstiden är 2–5 vardagar från att avsändaren har lämnat paketet till Posti. Med hemleverans vid överenskommen tid (ibland kallad Hempaket) väljer du själv en tidsperiod på cirka fyra timmar mellan kl. 9 och 21 på vardagar. Lyckas hemleveransen inte lämnar Posti paketet på närmaste uthämtningsställe och meddelar dig.

**Ändra leveransen.** I OmaPosti kan du byta uthämtningsställe, beställa hemleverans eller förlänga hämtningstiden. `Vaihda kotiinkuljetukseen` (Byt till hemleverans) fungerar för paket bara så länge avsändaren inte har lämnat det till Posti. Adressen kan bara avsändaren ändra, inte du som mottagare. Kundtjänsten kan däremot flytta paketet till ett annat uthämtningsställe mot en transportavgift, som du betalar när du hämtar ut det. En adressändring påverkar inte paketleveranser.

**Hämtas inte ut.** Hämtar ingen ut paketet i tid returnerar Posti det till avsändaren.

**Postförskott.** Ett postförskottspaket får du ut först när du har betalat det belopp som avsändaren har bestämt. Går paketet till en automat får du ett sms med en länk till betalningen. När du har betalat får du automatiskt ett sms med automatens uppgifter och öppningskoden till boxen.

**Returnera.** Erbjuder webbutiken kostnadsfri retur via Posti öppnar du försändelsen i OmaPosti och väljer `Returnera paket`. Sedan väljer du paketstorlek och inlämningsställe och reserverar vid behov en box i automaten.

## Paketet är försenat, borta eller skadat

**Borta.** Hörs det inget av paketet kan avsändaren eller du som mottagare be Postis kundtjänst om en utredning. Hittas det inte trots sökningar ansöker avsändaren om ersättning, eftersom Posti ersätter den som har betalat för transporten, och det är nästan alltid avsändaren. Har du handlat på nätet ansvarar säljaren för transporten enligt konsumentskyddslagen, så kom överens med webbutiken om en ny vara eller om att häva köpet. Enligt Konkurrens- och konsumentverket (KKV) kan du anmäla ett paket inom Finland som försvunnet om det inte har lämnats ut inom 14 dagar efter den avtalade leveranstiden. Har ingen leveranstid avtalats räknas paketet som försvunnet om det inte har levererats inom 28 dagar från att det togs emot för transport.

**Från utlandet.** Försvinner eller skadas en internationell försändelse har avsändaren rätt till ersättning. Du som mottagare kan kräva skadestånd bara om avsändaren skriftligen avstår från sina rättigheter till förmån för dig.

**Levererat, men inget paket.** Var det ett Småpaket kan det ligga i postlådan, så kolla där först. Kontakta annars kundtjänsten, så utreder Posti var paketet finns.

**Skadat.** Säg genast till den Posti-anställda som lämnar ut paketet. Kommer det hem till dig antecknar du skadan på fraktdokumenten tillsammans med föraren. Fotografera förpackningens yttre skador, hur produkten var skyddad inuti och den skadade produkten. Anmäl skadan till Posti med formuläret för skadade försändelser och meddela avsändaren. Har du köpt varan kontaktar du också säljaren, som ansvarar för att den är hel när den kommer fram.

**Reklamera.** Reklamera skriftligen, till exempel per e-post, så snart som möjligt efter att du har upptäckt felet. Har du handlat på nätet reklamerar du till webbutiken.

**Om ni inte blir överens.** Kontakta KKV:s konsumentrådgivning, eller Europeiska konsumentcentrumet om säljaren finns i ett annat EU-land, Norge, Island eller Storbritannien. Därefter kan du föra tvisten till Konsumenttvistenämnden. Hoppar du över konsumentrådgivningen kan nämnden låta bli att behandla ärendet. Handläggningen är kostnadsfri, och begäran gör du på finska eller svenska, helst i nämndens e-tjänst. Traficom avgör klagomål om utdelning av brev och andra brott mot postlagen, men inte frågor om ersättning: en tvist med Posti om skadestånd kan du föra till Konsumenttvistenämnden.

## Tull på paket från länder utanför EU

**Förtullning.** Allt du beställer från länder utanför EU måste förtullas, och det gäller också varor från vissa områden inom EU, som Åland och Kanarieöarna. Posti meddelar dig när ett paket behöver förtullas, och du förtullar det i Tullens tjänst på tulli.fi eller i OmaPosti. Ett köp på högst 150 euro där du redan har betalat momsen i webbutiken (ett så kallat IOSS-köp) förtullar Posti åt dig, om butiken har skickat med alla uppgifter som behövs. Annars förtullar du själv.

**Tidsfrist.** Paketet väntar i Postis lager medan du förtullar det, i högst 20 dygn från att det kom till Finland. Förtullar du inte inom den tiden skickas det tillbaka till avsändaren, och mer tid kan du inte få.

**Avgifter.** Sedan den 1 juli 2026 betalas tullavgift på alla köp från länder utanför EU: för köp på högst 150 euro är den 3 euro per varupost. Från och med den 1 november 2026 kommer Tullen också att ta ut en behandlingsavgift på 2 euro per varupost. Har du betalat skatt vid förtullningen tar Posti dessutom ut en behandlingsavgift på 3,10 €, som du betalar på Postis webbplats med försändelsekoden, eller 3,30 € på ett serviceställe.

> Bluffmeddelanden i Postis namn kan innehålla en äkta försändelsekod och dyka upp i samma meddelandekedja som Postis riktiga ankomstavier. De ber dig boka en leveranstid, bekräfta adressen, förtulla paketet eller betala en transportavgift. Posti skickar inga länkar för förtullning eller betalning av behandlingsavgifter och ber dig aldrig identifiera dig med Apple ID. Ankomstavier för postförskott har däremot en länk för betalningen. Radera meddelandet utan att öppna det eller länken. Har du lämnat ut dina bankuppgifter, kontakta genast banken och gör sedan en polisanmälan.

## Postis kundtjänst

Så når du Posti enligt Postis svenska sidor den 26 oktober 2026:

- **Telefon:** 0100 5577. Samtalet kostar lokalnätsavgift eller mobilsamtalsavgift, också under kötiden. Öppettiderna står bland kontaktuppgifterna på [Postis kundtjänst](https://www.posti.fi/sv/kundtjanst).
- **Chatt:** i OmaPosti-appen.
- **Formulär:** formuläret för att rapportera en skadad försändelse finns under [Skadefall](https://www.posti.fi/sv/kundtjanst/skadefall).

## Spåra Posti-paket med Peek

[Peek](/) ser direkt att en kod som börjar på `JJFI` är Postis. Klistrar du in en länk från Postis uppföljning, med `/uppfoljning/`, `/seuranta/` eller `/tracking/` följt av koden (också efter en språkdel som `/sv/`), vet Peek att det är Posti, oavsett kod. Du behöver inget postnummer.

För en kod med två bokstäver, nio siffror och `FI` frågar Peek Posti först, och även [Chronopost](carrier:chronopost), och väljer Posti om Posti känner till koden. Känner Posti inte till den ännu ser du "Not found at … yet. Peek will keep checking after you add it." Har du lagt till paketet i ett Peek-konto kollar Peek sedan koden varje timme de första 6 timmarna och därefter allt glesare. Utan konto upphör kontrollerna efter 6 timmar, om du inte slår på "Notify me when it’s found": då kollar Peek en gång om dagen tills 30 dagar har gått sedan du lade till koden. Stämmer kontrollsiffran inte frågar Peek inte Posti, så välj Posti med "Change". För en SSCC-kod på 20 siffror frågar Peek Posti och andra transportörer som koden passar, till exempel [Matkahuolto](carrier:matkahuolto) och [PostNord](carrier:postnord). Känner flera av dem till koden väljer du själv. En utländsk kod spårar du hos Posti med "Change" eller via en länk från posti.fi, och vissa internationella paket följer Peek själv vidare till Posti.

Peek läser Postis uppföljning på engelska, så du ser Postis engelska händelserader, till exempel `Item is ready for a pick up` (paketet väntar på dig) och `Item accepted from transport` (paketet är fortfarande på väg, trots ordalydelsen). Förklarar Posti en händelse står förklaringen efter raden. Peek visar orten för varje skanning på en karta, paketets vikt och mått och, när paketet väntar på ett uthämtningsställe eller har hämtats där, ställets namn och adress. Ortsangivelsen `ULKOMAILLA`, finska för utomlands, tar Peek bort. Uthämtningskoden och din adress tar Peek inte med, och Peek visar varken beräknad leveranstid eller avsändare. En kod som gäller flera paket spårar Peek inte, och gamla försändelser hittas inte. På dagtid kollar Peek paketet var tionde minut och varannan minut när det är ute för leverans, på natten var eller varannan timme, och Peek kan meddela dig när något händer. Peek kan inte kontakta Posti eller webbutiken åt dig.

## Frågor om att spåra Posti-paket

### Hur spårar jag mitt Posti-paket på svenska?

Öppna posti.fi/sv/uppfoljning och skriv in försändelsekoden. Rubrikerna visas då på svenska. I OmaPosti ser du dessutom de paket vars uppgifter innehåller ditt telefonnummer.

### Hur länge ligger paketet kvar i automaten?

Posti ber dig hämta det inom 5 dagar, och den sista dagen står i ankomstavin. I OmaPosti kan du förlänga tiden med upp till 14 dagar mot en avgift på 1,90 € (per dag enligt Postis finska sida).

### Hur länge tar leveransen?

Småpaket till dörren kommer fram 2–5 vardagar efter att avsändaren har lämnat paketet till Posti. För andra paket visar Postis uppföljning en beräknad leveranstid, för paket som ska förtullas först efter förtullningen.

### Har Postis kundtjänst en chatt?

Ja, i OmaPosti-appen. Du kan också ringa 0100 5577.

### Vem kräver ersättning om paketet försvinner?

Avsändaren, eftersom Posti ersätter den som har betalat för transporten. Har du köpt något på nätet reklamerar du till webbutiken, som ansvarar för transporten.

:::sources
- [Posti: Uthämtning av försändelse](https://www.posti.fi/sv/mottagning/uthamtning-av-forsandelse) – ankomstavi, 5 dagar, förlängning, koder, identitetsbevis, fullmakt, varför inte automaten, ändringar, retur, beräknad leveranstid, OmaPosti, telefon, chatt
- [Posti: Lähetyksen noutaminen](https://www.posti.fi/vastaanottaminen/lahetyksen-noutaminen) – på finska: förlängningen kostar 1,90 € per dag
- [Posti: Spåra försändelser](https://www.posti.fi/sv/uppfoljning) – statusrubrikerna och förklaringsraderna, Ingen försändelse hittades
- [Posti: Hemleverans](https://www.posti.fi/sv/mottagning/hemleverans) – Småpaket till dörren, 2–5 vardagar, överenskommen tid, misslyckad hemleverans, levererat men inget paket
- [Posti: Kotiinkuljetus](https://www.posti.fi/vastaanottaminen/kotiinkuljetus) – på finska: leveranstiden räknas från att Posti har fått paketet
- [Posti: Postis automater](https://www.posti.fi/sv/sandning/paket/paketautomater) – knappsats, pekskärm, låsning efter 3 försök, automatens namn, retur i OmaPosti
- [Posti: OmaPosti](https://www.posti.fi/sv/omaposti) – byta uthämtningsställe, beställa hemleverans, förlänga hämtningstiden
- [Posti: Postförskottspaket](https://www.posti.fi/sv/sandning/paket/postforskottspaket) – betalning före uthämtning, sms med betalningslänk
- [Posti: Förtullning](https://www.posti.fi/sv/mottagning/fortullning) – tulli.fi och OmaPosti, 3,10 € och 3,30 €, IOSS, tidsfristen, Tullens 2 euro från 1 november 2026
- [Posti: Beställning och förtullning från utlandet](https://www.posti.fi/sv/mottagning/fortullning/bestallning-och-fortullning-fran-utomlands) – 20 dygn i lagret, tullavgift på 3 euro, Åland och Kanarieöarna
- [Posti: Vad ersätter Posti och till vem](https://www.posti.fi/sv/kundtjanst/skadefall/vad-ersatter-posti-och-till-vem) – utredning, ersättning, säljarens ansvar, skadat paket
- [Posti: Rapportera en skadad försändelse](https://www.posti.fi/sv/kundtjanst/skadefall/rapportera-skadad-forsandelse) – formulär, bilder, meddela avsändaren
- [Posti: Skadefall](https://www.posti.fi/sv/kundtjanst/skadefall) – formulären
- [Posti: Informationssäkerhet och bluffmeddelanden](https://www.posti.fi/sv/kundtjanst/informationssakerhet-och-bluffmeddelanden) – bluffmeddelanden, inga betalningslänkar, postförskott, Apple ID, bank och polisanmälan
- [Posti: Dataskyddsbeskrivning för försändelseuppföljningen](https://www.posti.fi/sv/kundtjanst/villkor-och-beskrivningar/dataskydd/dataskyddsbeskrivningar/dataskyddsbeskriving-for-forsandelseuppfoljningen) – sökning bara med exakt försändelsekod, samtalsavgift
- [Posti: Kundtjänst](https://www.posti.fi/sv/kundtjanst) – kontaktuppgifter och öppettider
- [Traficom: Postkundernas rättigheter](https://www.traficom.fi/sv/postverksamhet-och-utdelning/postkundernas-rattigheter) – reklamation till postföretaget, Traficoms klagomål, skadestånd till Konsumenttvistenämnden
- [KKV: Post- och paketdistribution](https://www.kkv.fi/sv/konsumentarenden/telefon-och-internetabonnemang/post-och-paketdistribution/) – avsändarens ansvar, försvunnet efter 14 eller 28 dagar, internationella försändelser
- [KKV: Lämna en reklamation till företaget](https://www.kkv.fi/sv/konsumentarenden/konsumentradgivning/lamna-en-reklamation-till-foretaget/) – skriftlig reklamation så snart som möjligt
- [Konsumenttvistenämnden: Innan du lämnar in ansökan](https://www.kuluttajariita.fi/sv/for-koparen/innan-du-lamnar-in-ansokan/) – reklamation, konsumentrådgivning, Europeiska konsumentcentrumet, kostnadsfri handläggning
- [Konsumenttvistenämnden: Övriga tjänster](https://www.kuluttajariita.fi/sv/for-koparen/att-lamna-in-en-ansokan/ovriga-tjanster/) – finska eller svenska, e-tjänsten
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – tullavgift på 3 euro från 1 juli 2026, avgift på 2 euro från 1 november 2026
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – vad Peek läser hos Posti
:::
