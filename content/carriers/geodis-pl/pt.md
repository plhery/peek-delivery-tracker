---
title: Rastreio GEODIS Polónia: número PL, estados e entrega
description: Segue um envio da GEODIS Polónia (antiga Pekaes) com número PL e 16 dígitos: significado dos estados, entrega, levantamento no terminal, reclamações e contacto.
slug: geodis-polonia-rastreio
published: 2026-11-18
updated: 2026-11-20
---

A GEODIS Poland é a rede polaca de transporte de paletes e encomendas do grupo francês GEODIS, detido pelos caminhos de ferro públicos franceses SNCF. A GEODIS adquiriu a PEKAES em 2021 e a rede é atualmente operada pela GEODIS Road Network sp. z o.o., sediada em Błonie, na Polónia. O que fazer depende do que o remetente te enviou:

- **Um número composto por `PL` e 16 dígitos, ou uma ligação para a Strefa Klienta:** trata-se de um envio da GEODIS Poland (encomenda ou palete). Cola o código ou a ligação na caixa acima, ou pesquisa diretamente no portal de seguimento da GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Outro número da GEODIS:** o Peek também lê o seguimento francês da [GEODIS](carrier:geodis), cujos números começam por `1G` (ver abaixo). A pesquisa em `geodis.com` localiza envios aéreos, marítimos e de última milha, remetendo o transporte rodoviário para a Strefa Klienta.
- **Ainda sem número:** é a loja online ou o remetente quem o faculta, habitualmente no e-mail de confirmação da encomenda ou na notificação de expedição.

## Número de seguimento da GEODIS Polónia: formato e onde encontrá-lo

As soluções da GEODIS destinam-se exclusivamente a clientes empresariais e profissionais, pelo que o código de envio está na posse da empresa ou loja online que expede a mercadoria. Se não constar nos teus e-mails, pede-o ao vendedor ou vê [como encontrar o número de seguimento](guide:find-tracking-number).

| Número (inventado) | O que é |
| --- | --- |
| `PL4829105736491827` | `PL` seguido de 16 dígitos: um número de envio da GEODIS Poland, designado `Shipment No.` na Strefa Klienta. |
| `1G4829105736` | `1G` seguido de 10 letras maiúsculas ou dígitos: um código da [GEODIS](carrier:geodis) em França, acompanhado no espaço de destinatários francês e não na Strefa Klienta. |

Na versão inglesa da Strefa Klienta, introduz o código no campo `Enter Shipment No.` e carrega em `Search` (na página polaca: `Wpisz numer przesyłki` e `Szukaj`). Se o portal responder `Shipment with such number does not exist`, desconhece esse número: contacta o remetente ou a loja online, tal como a GEODIS recomenda quando um código não funciona. Se surgir a mensagem em polaco `Proszę wprowadzić właściwy numer przesyłki.`, pede um número de envio válido: confirma se não há gralhas ao copiá-lo.

## O que significam os estados de seguimento da GEODIS Polónia

A Strefa Klienta está disponível em polaco e em inglês. Quando encontra a remessa, a página inglesa apresenta primeiro um resumo (`Shipment No.`, `Status`, `Delivery Country`, `Delivery Location` com código postal e localidade) e a ligação `Details` abre o histórico de leituras com data, hora e localidade do terminal. Eis as designações em inglês pela ordem habitual do transporte:

| Estado na GEODIS | O que significa para ti |
| --- | --- |
| `Received from the sender` | A GEODIS recolheu o envio nas instalações do remetente (`Collected (SMS)` confirma a recolha por SMS; `Failed collection (SMS)` indica recolha falhada). |
| `Inbound Scan From Driver to Terminal` | Um motorista deu entrada do envio num terminal: o de origem após a recolha ou, se antes figurava `Outbound for Delivery`, o de distribuição no final de uma volta. Se imediatamente antes não constar `Consignee Absent` nem `Failed delivery (SMS)`, nessa volta não houve tentativa de entrega e a remessa aguarda a volta seguinte. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Saiu do terminal de origem num camião de linha rumo ao terminal que serve a tua morada. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | Chegou ao terminal responsável pela entrega final. |
| `Awaiting Delivery` | Aguarda nesse terminal pela inclusão numa volta de distribuição. |
| `Outbound for Delivery` | Segue no veículo de distribuição a caminho da tua morada. |
| `Consignee Absent` | O motorista não te encontrou na morada. |
| `Failed delivery (SMS)` | Tentativa de entrega falhada, confirmada por SMS. |
| `Awaiting designation of new delivery date` | A GEODIS aguarda a marcação de uma nova data de entrega. |
| `Awaiting Decision` | A GEODIS aguarda instruções do cliente ordenante ou do remetente. |
| `Delivery Refusal` | O destinatário recusou a receção. |
| `Request for Return` | A GEODIS tem instrução de devolução: a remessa está a regressar ao remetente, ainda sem lhe ter sido entregue. |
| `Delivered` | Entregue (`Delivered (SMS)` significa o mesmo, confirmado por SMS). |
| `Claim Received` | Foi registada uma reclamação: trata-se apenas de um registo administrativo sem movimentação da carga. |

Alguns registos assinalam uma retenção ou incidente: `Stopped at Hub` e `Stopped at Collection Terminal` (remessa retida num centro de triagem ou no terminal de origem), `Not Dispatched` (perdeu a ligação ou volta prevista, mas continua na rede) e `Failure` (avaria ou anomalia). Uma linha iniciada por `Pseudoscan` (`Pseudoscan – delete all previous 100, 400, 500 scans`) é um movimento interno que anula os registos anteriores de entrega, devolução e reclamação. Mais explicações gerais em [estados de seguimento](guide:tracking-statuses).

## Entrega da GEODIS na Polónia: morada particular, aviso prévio e levantamento no terminal

O Regulamento Geral de Prestação de Serviços (*Ogólny Regulamin Świadczenia Usług*) da GEODIS Road Network, em vigor desde 4 de novembro de 2024, vincula a GEODIS ao seu ordenante (*Zleceniodawca*, a empresa que contrata o transporte, normalmente a loja) nos envios nacionais na **Polónia** e no transporte rodoviário internacional:

- **Ausência na entrega.** Nos serviços nacionais na **Polónia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`, `PTL DOM`), se estiveres ausente o representante deixa aviso da tentativa; após uma segunda tentativa sem sucesso por motivos imputáveis ao destinatário, o envio é devolvido ao remetente a expensas do ordenante (tal como em caso de recusa ou morada incorreta). Nos serviços rodoviários internacionais (`PAC INT`, `LTL INT`, `PTL INT`), perante a ausência do destinatário, o ordenante ou o remetente dispõe de até 2 dias úteis após a tentativa falhada para dar instruções antes de a GEODIS devolver a remessa a cargo do ordenante. Consulta [entrega falhada](guide:missed-delivery) e [devolvido ao remetente](guide:returned-to-sender).
- **Paletes para morada particular na Polónia (`DDOP`).** Na grupagem nacional na **Polónia** (`LTL DOM` e `LTL DOM NST`), as entregas em moradas particulares realizam-se exclusivamente nos dias úteis, de segunda a sexta-feira, das `8:00` às `16:00`. O ordenante tem de indicar o teu número de telemóvel e assinalar o envio com `DDOP`: a GEODIS envia mensagens de estado e combina previamente a entrega por telefone, SMS ou e-mail, efetuando o contacto telefónico o mais tardar no próprio dia em que pode entregar. Se em 2 dias úteis não conseguir contactar-te para agendar, fala com o ordenante e devolve o envio ao remetente.
- **Descarga e serviços adicionais nacionais na Polónia.** Ao abrigo do serviço `DDOP` na **Polónia**, o motorista descarrega a palete ao nível do solo no local de entrega, ou no ponto pavimentado e seguro mais próximo caso as condições da via impeçam a paragem segura junto à porta. A entrega dentro da habitação (*Usługa Wniesienia*: nacional em `LTL DOM` / `LTL DOM NST`, até 300 kg brutos, máximo de 30 kg por volume e 30 volumes, até 150 m por piso pavimentado e até ao segundo piso na falta de elevador operacional, podendo alargar o prazo em até 3 dias úteis), a deslocação com porta-paletes (*Usługa Wwiezienia*, até 1.200 kg brutos e 300 kg por palete num raio de 150 m até à primeira barreira arquitetónica), o dia fixo (*Fix Day*) e a entrega até às `10:00` (`D10`) ou `12:00` (`D12`), ambas apenas em códigos postais polacos selecionados, são opções nacionais autónomas que o ordenante tem de contratar.
- **Levantamento em terminal na Polónia.** Quando o ordenante instrui o levantamento presencial num terminal da GEODIS por um destinatário particular, o envio fica disponível durante 48 horas (contadas em dias úteis) a partir dessa instrução, de segunda a sexta-feira entre as `11:00` e as `16:00`; findo esse prazo, regressa ao remetente.
- **Envio à cobrança na Polónia (*Usługa pobrania*).** Disponível apenas nos serviços nacionais na **Polónia** (`PAC DOM`, `LTL DOM`, `LTL DOM NST`), permite pagar antes de receber a encomenda até `10.000 PLN` (ou o equivalente noutra moeda) por envio em numerário ou através de cartão de pagamento, BLIK ou outro meio eletrónico aceite pela GEODIS.
- **Assinatura e verificação.** Confirmas a receção assinando no terminal móvel do motorista ou nos documentos de transporte. O regulamento da GEODIS determina que o destinatário não pode verificar o conteúdo antes de confirmar a receção: inspeciona por isso atentamente a embalagem exterior e regista quaisquer reservas nos documentos de transporte no ato da assinatura.

## Envio da GEODIS Polónia atrasado, danificado ou não entregue

**Sem atualizações.** Se passarem vários dias sem novas leituras, consulta [rastreio que não atualiza](guide:tracking-not-updating) e fala com a loja.

**Atraso segundo o direito do consumidor polaco.** Na **Polónia**, a autoridade de defesa do consumidor (UOKiK, citando os artigos 546 e 546¹ do Código Civil polaco) recorda que nas vendas de consumo sujeitas à lei polaca o vendedor deve entregar o bem sem demora e no máximo no prazo de 30 dias a contar da celebração do contrato, salvo acordo em contrário. Ultrapassado esse prazo, o comprador pode fixar um prazo suplementar ao vendedor e, mantendo-se a falta de entrega, resolver o contrato.

**Mercadoria danificada ou em falta.** Havendo danos exteriores visíveis ou faltas parciais no momento da entrega, lavra de imediato um auto de avaria (*protokół szkodowy*) na presença do motorista segundo o modelo oficial da GEODIS, assinado de forma legível por ambos. As reservas relativas a danos ou faltas não visíveis no ato da entrega devem ser apresentadas à GEODIS antes de decorridos 7 dias sobre a receção; em qualquer caso, cabe ao reclamante provar que o dano ou perda parcial ocorreu antes da entrega. Mantém os bens à disposição da GEODIS para vistoria até ao encerramento do processo e tira fotografias com data e hora. O formulário de reclamação da GEODIS adverte que a assinatura do auto de avaria serve de base à apreciação, mas não equivale à apresentação de uma reclamação. Vê também [encomenda danificada](guide:damaged-parcel).

**Quem apresenta a reclamação.** Nos termos do regulamento da GEODIS Road Network, as reclamações são dirigidas por escrito ao departamento de reclamações (*Dział Reklamacji*) na sede em Błonie pelo ordenante ou por quem detenha procuração escrita ou cessão de créditos do ordenante, sem demora e no prazo máximo de 14 dias após a receção (avaria ou falta) ou de 21 dias após a data prevista de entrega (atraso ou perda total). Nas compras de consumo regidas pela lei polaca, a UOKiK (art. 548 § 3 do Código Civil polaco) esclarece que o vendedor responde pelo risco de perda ou dano acidental até à entrega ao comprador quando o modo de envio constava entre os propostos pela loja, mesmo que o dano tenha sido causado pela transportadora: enquanto comprador, reclamas junto do vendedor, cabendo a este reclamar junto do transportador.

## Contactos e apoio ao cliente da GEODIS Polónia

- **Apoio ao cliente na Polónia (*Dział Obsługi Klienta*):** `+48 22 460 26 26`, indicado na página polaca da Strefa Klienta (sem horário de atendimento publicado).
- **Reclamações por correio:** `Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie`, Polónia. Os impressos de reclamação (`GRN Formularz zgloszenia reklamacji`) e de auto de avaria (`GRN Protokol szkody`) encontram-se para transferência na Strefa Klienta.
- **Instalações na Polónia:** a [página de contacto polaca da GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) lista 42 localizações na Polónia com morada, incluindo as delegações da GEODIS Road Network.
- **Formulário online em `geodis.com`:** destinado exclusivamente a profissionais e empresas, exigindo nome da empresa (`Firma *`) e e-mail profissional (`Adres e-mail służbowy *`).

## Seguir uma encomenda da GEODIS Polónia com o Peek

O formato `PL` seguido de 16 dígitos não pertence em exclusivo à GEODIS (`PL` identifica a Polónia), pelo que o Peek não consegue confirmar a GEODIS Poland apenas pelo aspeto do código. Cola-o ainda assim: o Peek acompanha mais de 3.500 transportadoras, consulta a GEODIS Poland e mostra «GEODIS Poland tem esta encomenda.» assim que o portal a reconhece. Se a GEODIS ainda não a listar, a encomenda apresenta «Provavelmente GEODIS Poland» e «À espera do primeiro registo de GEODIS Poland» enquanto o Peek continua a verificar; se ao fim de uma semana continuar sem constar, o Peek sugere confirmar o código com o remetente ou escolher outra transportadora. Para definires manualmente a transportadora, toca em «Alterar» na linha da transportadora e escolhe «GEODIS Poland» em «Corresponde a este número», ou procura-a (`Pekaes` e `GEODIS Road Network` também a encontram; «GEODIS» isolado refere-se à GEODIS França). Numa encomenda já adicionada, usa «Alterar transportadora». Uma ligação para a Strefa Klienta com `ShNumber=` é associada de imediato à GEODIS Poland.

O Peek lê a Strefa Klienta em inglês: o código `100` (`Delivered`) surge traduzido («Entregue»), enquanto as restantes descrições de leitura mantêm a redação inglesa da GEODIS (incluindo `Delivered (SMS)`), juntamente com a localidade do terminal, a hora de entrega e o país de destino. As leituras em terminais polacos são interpretadas na hora de Varsóvia (`Europe/Warsaw`); os terminais no estrangeiro conservam o seu relógio local. O Peek converte os horários conhecidos para o fuso horário do teu dispositivo; em `peektracker.com` e nas aplicações para iPhone e Android, o histórico termina com «Horas no teu fuso horário:» e o nome do fuso. Quando uma linha `Pseudoscan` anula leituras anteriores, o Peek também as remove, e `Claim Received` mantém inalterada a fase do envio. O portal da GEODIS não indica data prevista de entrega, peso, designação do serviço, nome do destinatário nem comprovativo de entrega; o Peek omite ainda o código postal e localidade de entrega, o número de volumes, os códigos internos de terminal e as observações onde pode constar quem assinou.

Enquanto mantiveres a encomenda aberta no ecrã, ou no dia da entrega com as notificações ativas, o Peek verifica até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 (hora local) no dia em que a GEODIS regista a saída para distribuição (`Outbound for Delivery`) — enquanto o próprio portal da GEODIS responder; nas restantes alturas, bem como após dois dias sem novidades (12 horas enquanto permanecer em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Através de serviços externos de seguimento, o Peek verifica de hora a hora, a cada 3 horas após um dia sem movimento e até a cada 15 minutos no dia da entrega enquanto tiveres a encomenda aberta ou as notificações ligadas. Podes ativar alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não permite agendar entregas, pedir levantamento em terminal nem submeter reclamações: liga para a GEODIS ou fala com a loja.

## Perguntas frequentes sobre o seguimento da GEODIS Polónia

### A GEODIS Poland é a mesma empresa que a Pekaes?

Sim. A GEODIS adquiriu a PEKAES em 2021 e a rede polaca é hoje operada pela GEODIS Road Network. O portal de seguimento continua em `strefaklienta.pekaes.geodis.pl`.

### A GEODIS faz entregas ao sábado na Polónia?

O Regulamento Geral da GEODIS Road Network não prevê entregas ao sábado: as paletes para moradas particulares na Polónia (`DDOP`) são entregues nos dias úteis de segunda a sexta-feira entre as `8:00` e as `16:00`, e nas encomendas nacionais (`PAC DOM`) os sábados, domingos e feriados não contam para o prazo de trânsito.

### O motorista da GEODIS leva a encomenda até dentro de casa na Polónia?

Apenas em envios nacionais de grupagem na Polónia (`LTL DOM` ou `LTL DOM NST`) quando o ordenante tiver contratado o serviço adicional de entrega no interior (*Usługa Wniesienia*): até 300 kg brutos (máximo de 30 kg por volume), até 150 m do local de descarga e até ao segundo piso caso não exista elevador utilizável. Sem esse serviço, a descarga é feita ao nível do solo.

### Quanto tempo demora a GEODIS a entregar na Polónia e na Europa?

Na página de transporte de paletes e encomendas, a GEODIS indica prazos standard de 24 a 48 horas na Polónia (3 a 5 dias na Europa) e entrega expresso no dia seguinte antes do meio-dia na Polónia (1 ou 2 dias na Europa). No Regulamento Geral, a GEODIS entrega por regra as encomendas nacionais `PAC DOM` até 30 kg em até 48 horas após a recolha (excluindo fins de semana e feriados) e a grupagem `LTL` em 2 dias úteis.

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking (em inglês)](https://strefaklienta.pekaes.geodis.pl/en/) – campo `Enter Shipment No.`, botão `Search` e impressos de reclamação e auto de avaria
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek (em polaco)](https://strefaklienta.pekaes.geodis.pl/pl/) – telefone do apoio ao cliente `+48 22 460 26 26` e Regulamento Geral
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, em vigor desde 4 de novembro de 2024 (em polaco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – tentativas nacionais e internacionais, opção `DDOP`, descarga, entrega no interior, levantamento em terminal, cobrança, assinatura, prazos e reclamações
- [GEODIS Road Network: Formularz zgłoszenia reklamacji (em polaco)](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – morada postal de reclamações em Błonie e alcance do auto de avaria
- [GEODIS: Śledź swoją przesyłkę (em polaco)](https://geodis.com/pl-pl/sledz-swoja-przesylke) – onde encontrar o código, envios aéreos/marítimos/última milha em `geodis.com`, reencaminhamento rodoviário e código sem resposta
- [GEODIS: Twój obszar roboczy (em polaco)](https://geodis.com/pl-pl/twoj-obszar-roboczy) – acesso à Strefa Klienta como plataforma de seguimento
- [GEODIS: Transport palet i paczek (em polaco)](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – serviços exclusivos para profissionais e prazos standard e expresso na Polónia e na Europa
- [GEODIS: Skontaktuj się z nami (em polaco)](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – formulário empresarial e lista das 42 instalações na Polónia
- [GEODIS: Historia (em polaco)](https://geodis.com/pl-pl/historia) – aquisição da PEKAES em 2021 e pertença à SNCF
- [UOKiK: Odpowiedzialność za przesyłkę (em polaco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – responsabilidade do vendedor até à entrega no direito do consumidor polaco
- [UOKiK: Wydanie towaru (em polaco)](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – prazo máximo de 30 dias, prazo adicional e resolução do contrato na Polónia
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formato `PL` de 16 dígitos, ligações Strefa Klienta, descrições de leitura, códigos `1G` da GEODIS França e dados lidos pelo Peek
:::
