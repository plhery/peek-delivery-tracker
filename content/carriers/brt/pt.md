---
title: Rastreio BRT: encomenda de Itália, estados e contacto
description: Segue a tua encomenda BRT vinda de Itália: onde encontrar o BRTcode, significado dos estados em inglês, entrega pela DPD Portugal e apoio ao cliente.
slug: brt-rastreio
published: 2026-11-17
updated: 2026-11-17
---

A BRT é uma transportadora expresso italiana que pertence ao grupo Geopost, tal como a [DPD Portugal](carrier:dpd-pt), a [SEUR](carrier:seur), a [Chronopost](carrier:chronopost) e a Jadlog. Podes seguir uma encomenda pela ligação enviada por SMS ou e-mail pela BRT, em [brt.it](https://www.brt.it/en/) ou colando o número acima. A BRT envia esse código — o *BRTcode* — assim que recebe o volume e o remetente partilha os teus contactos.

## Número de rastreio BRT: onde encontrá-lo, formatos e avisos reais

O *BRTcode* consta nos SMS e e-mails da BRT e no aviso deixado pelo estafeta após uma tentativa falhada. Sem mensagem? Verifica o e-mail de envio da loja ou consulta [como encontrar o número de seguimento](guide:find-tracking-number).

Quando a loja fornece o teu telemóvel ou e-mail, a BRT envia avisos legítimos com o *BRTcode* após a recolha e notificações de tentativa falhada, entrada em armazém (*giacenza*) ou chegada a um *BRT-fermopoint* ou *BRT Locker*. Em 16 de abril de 2026, contudo, a empresa publicou um [alerta de phishing por SMS e e-mail](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) sobre falsos avisos de não entrega e faturas falsas com software malicioso: repara em erros gramaticais, remetentes genéricos ou ligeiramente alterados e apelos urgentes para clicar em ligações e, na dúvida, escreve o *BRTcode* diretamente em `brt.it` ou `vas.brt.it` (lê também [como identificar SMS falsos de entregas](guide:delivery-scam-texts)).

Formatos aceites pelo Peek para a BRT (exemplos inventados):

| Número (inventado) | O que identifica |
| --- | --- |
| `182094736105` | 12 dígitos: número de expedição (*numero spedizione*) atribuído pela BRT. |
| `06482917350428` | 14 dígitos: um *BRTcode*. |
| `918273645019283` | 15 dígitos: identificador de volume (*ID collo cliente*) criado pelo remetente. |
| `EOB19283746501928374` | `EOB` e 17 dígitos: outro identificador de volume do remetente. |

O formulário avançado da BRT pede 19 dígitos no campo *BRTcode*; o Peek não aceita códigos de 19 dígitos para a BRT. O código de segurança de 3 dígitos (*codice di sicurezza*), usado para alterar uma entrega, não é um número de rastreio.

Se a BRT não devolver dados, confirma se há erro de digitação ou aguarda a recolha. A [pesquisa avançada da BRT](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) (*Rintraccia spedizione*) abrange 12 meses com *BRTcode* ou número de 12 dígitos, mas apenas 2 meses com um *ID collo cliente*. Códigos de 14 dígitos também são usados pela [DPD Portugal](carrier:dpd-pt) e pela [SEUR](carrier:seur), e os de 12 dígitos pela [Mondial Relay](carrier:mondial-relay) ou Colis Privé: confirma a transportadora no e-mail da loja ou vê [os formatos dos números de seguimento](guide:tracking-number-formats).

## Estados de rastreio da BRT: o que significa cada mensagem

O seguimento em `vas.brt.it` abre em italiano e muda para inglês, francês ou alemão (sem português). O Peek lê a versão inglesa, onde as colunas ficam em italiano: *Data* (data), *Ora* (hora), *Filiale* (filial BRT) e *Evento* (evento). O histórico do Peek mostra estas mensagens em inglês, exceto em qualquer entrega — incluindo num *BRT-fermopoint* ou *BRT Locker* — cuja linha surge como `Delivered` (com o Peek em italiano vês os textos italianos da BRT e `Consegnata`, mantendo em inglês leituras conhecidas apenas nessa língua, como as de cacifo):

| O que a BRT escreve | O que significa para ti | Etapa no Peek |
| --- | --- | --- |
| `SHIPPING INFO SENT TO BRT` | A BRT recebeu os dados do remetente, mas ainda não tem o volume. Vê [estados de seguimento](guide:tracking-statuses). | «Etiqueta criada» |
| `COLLECTED` | A BRT recolheu a encomenda junto do remetente. | «Aceite pela transportadora» |
| `DEPARTED` | Saiu de uma filial ou centro de triagem da BRT. | «Em trânsito» |
| `ARRIVED AT DEPOT` | Chegou à filial indicada em *Filiale*. | «Em trânsito» |
| `FOR DELIVERY` | O estafeta saiu para distribuição com a encomenda. | «Em distribuição» |
| `NOTICE OF ATTEMPTED DELIVERY` | Ninguém presente; o estafeta deixou aviso. | «Tentativa de entrega» |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Não entregue: destinatário desconhecido ou morada incompleta. | «Tentativa de entrega» |
| `TURMOIL` | Uma manifestação pública atrasa o transporte. | «Problema reportado» |
| `PATRONAL HOLIDAY` | Feriado do padroeiro no destino: o volume aguarda o dia antes da filial de entrega, sem tentativa. | «Em trânsito» |
| `ARRIVED AT BRT-fermopoint` | Pronta para levantamento num ponto *BRT-fermopoint*. | «Pronta para levantamento» |
| `COLLECTED AT BRT-fermopoint` | Levantada por ti no *BRT-fermopoint*. | «Entregue» |
| `ARRIVED AT BRT LOCKER` | Pronta para levantamento num cacifo *BRT Locker*. | «Pronta para levantamento» |
| `COLLECTED AT BRT LOCKER` | Retirada por ti do *BRT Locker*. | «Entregue» |
| `TO BE DELIVERED` | O parceiro no estrangeiro tem-na para distribuição (registado no mesmo minuto que `FOR DELIVERY`). | «Em trânsito» (com `FOR DELIVERY`: «Em distribuição») |
| `ABSENT AFTER ATTEMPT.DELIVERY` | O parceiro estrangeiro não encontrou ninguém; segue-se nova volta ou devolução. | «Tentativa de entrega» |
| `To collect at the parcel shop` | Deixada na loja de recolha do parceiro estrangeiro. | «Pronta para levantamento» |
| `DELIVERED` | Entregue. | «Entregue» |
| `RETURNED TO SENDER` | A regressar ao remetente. | «Devolvida ao remetente» |

Três notas: `COLLECTED` é a recolha no vendedor, e se a encomenda entrou em Itália pela [Chronopost](carrier:chronopost) ou DPD assinala a passagem à BRT («Em trânsito»). Um `FOR DELIVERY` num armazém DPD estrangeiro representa a entrega a essa rede («Em trânsito»). E se `DELIVERED` surgir após `RETURNED TO SENDER`, o volume regressou ao remetente: a linha indica `Delivered`, mas a etapa no Peek mantém-se «Devolvida ao remetente».

## Encomenda da BRT de Itália para Portugal (e fora da UE)

**Quem entrega.** Por estrada para a Europa, a BRT usa a rede DPD (e outro parceiro para cargas pesadas ou volumosas). Em envios DPD, a [ajuda do detalhe da BRT](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) refere uma caixa de acesso ao seguimento DPD. No Peek, se a expedição tiver um único volume, a BRT indicar o código DPD de 14 dígitos (*Parcel number dpd*) e a última leitura vier de um armazém DPD que identifique Portugal, o Peek mostra «Entrega: DPD Portugal» e consulta a [DPD Portugal](carrier:dpd-pt) (enquanto estiver só anunciada: «Anunciada a DPD Portugal, ainda não recebida»). Assim que surja na DPD uma leitura datada atualizada, o Peek segue na DPD Portugal mantendo o histórico italiano e consulta a BRT por mais um dia, no máximo a cada 55 minutos. Em vários volumes, fica na BRT.

**Alfândega.** De Itália para Portugal a encomenda circula na UE, sem alfândega; para destinos fora da UE (como o Brasil, onde a Geopost opera com a Jadlog), consulta [encomendas na alfândega](guide:customs).

## Entrega falhada em Itália: nova tentativa, BRT-fermopoint e Locker

Em caso de ausência em Itália, a BRT avisa por e-mail, SMS ou talão. Segundo a *Carta dei servizi*, agenda nova tentativa no dia útil seguinte; se falhar de novo, a filial telefona-te.

Pela ligação da mensagem ou em myBRT (com *BRTcode* e código postal ou PIN), podes escolher outro dia ou janela, desviar para *BRT-fermopoint* ou filial, libertar armazenagem ou mudar a morada com acordo do remetente. Mais em [entrega falhada](guide:missed-delivery).

**Em armazém (*in giacenza*).** Motivos: destinatário ausente ou desconhecido, morada errada ou incompleta ou recusa; consta no detalhe. Podes dar instruções pela mensagem ou seguimento (por vezes com o código de segurança de 3 dígitos). A BRT consulta também o remetente: segundo a *Carta dei servizi*, sem resposta escrita em 10 dias após o aviso, devolve o envio (as condições gerais remetem para os dias acordados com o remetente). O detalhe indica depois se há custos de armazenagem e quem os suporta.

**Na filial (*fermo deposito*).** Levanta com identificação ou envia alguém com autorização escrita, identificação própria e cópia da tua. Com os teus contactos, a BRT envia morada, horários e eventual cobrança.

**BRT-fermopoint.** Lojas parceiras (quiosques, tabacarias, papelarias…), algumas abertas ao fim de semana. Aviso por e-mail ou SMS e 10 dias de guarda.

**BRT Locker.** Cacifos em lojas, centros comerciais e postos de combustível — muitos no exterior 24/7 — abertos por QR ou PIN. Volumes acima de 20 kg não entram em *Locker* nem em *BRT-fermopoint*. Se o ecrã falhar ou estiver vazio, usa o formulário no rodapé do e-mail de recolha. Vê [cacifos de encomendas](guide:parcel-lockers).

## Encomenda BRT atrasada, danificada ou extraviada

Os prazos da BRT são indicativos. Se parar vários dias, contacta a filial de *Filiale* ou a loja titular do contrato e lê [seguimento que não atualiza](guide:tracking-not-updating).

**Dano ou extravio.** Se pagaste o porte, participa no formulário de suporte da BRT com fotografias se houver dano; caso contrário, avisa o vendedor. Perante dano visível na entrega, assina com reserva específica («*con riserva di controllo*» descrevendo a anomalia), pois reservas genéricas não contam segundo a BRT. Danos ocultos participam-se por escrito em 8 dias em Itália (art. 1698.º do Código Civil italiano) ou em 7 dias sem domingos nem feriados no transporte rodoviário internacional (convenção CMR). Mais em [encomenda danificada](guide:damaged-parcel).

**Reclamação e conciliação.** Destinatário e remetente podem reclamar transferindo o PDF *Modulo di Reclamo* em [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) e anexando-o em `services.brt.it/it/form-agcom`. A BRT responde em 45 dias. Depois podes pedir conciliação para `conciliazioni@brt.it`, por exemplo via *Casa del Consumatore* (quota anual de 50 €, ou 25 € até 50 €, reembolsada pela BRT havendo acordo) ou outra entidade, antes da AGCOM. Dada como entregue sem a teres? Consulta [entregue mas não recebida](guide:delivered-not-received).

## Apoio ao cliente e contactos da BRT

- **Formulários** na página inglesa [Support](https://services.brt.it/en/support) conforme o caso (atraso, dano, entregue mas não recebida). Não indica telefone.
- **Linha gratuita italiana** `800 278 708`, seg.–sex. 8:30–18:30 segundo a *Carta dei servizi* (a BRT não refere se funciona do estrangeiro).
- **E-mail** `assistenzaclienti@brt.it` segundo a *Carta*, indicando nome e apelido, códigos postais de recolha e entrega e *BRTcode*.
- **Chat** em `brt.it` ou filial na [pesquisa de filiais](https://services.brt.it/en/customer-service/search-for-a-branch). O número de Bolonha na *Carta* é da sede administrativa.

## Seguir encomendas BRT com o Peek

Cola o número no [Peek](/). Nos códigos `EOB` e na maioria dos 15 dígitos, o Peek consulta só a BRT (antes da [FedEx](carrier:fedex) se também passar na validação desta); nos de 12 dígitos, consulta a BRT logo no primeiro grupo. Nos 14 dígitos, pergunta de imediato à BRT em Itália e, em Portugal ou no Brasil, após os cinco primeiros candidatos (em Portugal, [DPD Portugal](carrier:dpd-pt), [SEUR](carrier:seur) e redes DPD; no Brasil, Jadlog e redes DPD), mostrando «A verificar serviços de seguimento…». Se necessário, toca em «Alterar» e escolhe a BRT em «Corresponde a este número» ou «Todas as transportadoras» (também por «Bartolini»). Ligações `vas.brt.it` com `brtCode=` funcionam; myBRT e 19 dígitos não.

O Peek lê a BRT sem navegador, marca os locais no mapa e mostra o peso, omitindo nomes, moradas, referência, volumes, cubagem e ponto de recolha. Em 14 dígitos pode mostrar a previsão da DPD ou [Chronopost](carrier:chronopost) enquanto válida. Lê as horas em Itália no fuso italiano (convertidas para o teu fuso), mantendo sem fuso as leituras num armazém que mencione Portugal por a BRT não explicitar se usa hora italiana ou local, e junta as leituras anteriores quando a [Chronopost](carrier:chronopost) ou DPD trazem a encomenda para Itália (com recurso à DPD se `vas.brt.it` estiver em manutenção).

O Peek verifica até a cada 10 minutos enquanto alguém aguarda a encomenda e de hora a hora nos restantes casos, bem como após dois dias sem leituras ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora». No dia da distribuição verifica até a cada 2 minutos desde as 6:00 locais (ou 30 minutos antes de uma janela da DPD ou Chronopost) enquanto a BRT ou DPD responderem diretamente (a cada 5 minutos se a DPD usar navegador); quando só respondem serviços de seguimento, verifica de hora a hora, a cada 3 horas após um dia sem leituras e até a cada 15 minutos no dia de entrega. Alertas: «Todas as leituras», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre o rastreio BRT

### Onde está a minha encomenda BRT neste momento?

Vê a linha mais recente: `ARRIVED AT DEPOT` indica a filial em *Filiale* e `FOR DELIVERY` que segue com o estafeta. Sem leituras ainda, o Peek mostra «À espera de BRT» (ou «À espera do primeiro registo de BRT» num código `EOB` antes de fixar transportadora).

### A BRT e a DPD são a mesma empresa?

Pertencem ambas ao grupo Geopost: a BRT opera a rede italiana e envia as encomendas rodoviárias europeias através da rede DPD.

### Quanto tempo demora uma entrega da BRT?

Indicativamente, a BRT refere 24–48 horas em Itália e 48–72 horas por estrada para a maioria dos países europeus. A *Carta dei servizi* (julho de 2026, volumes até 31,5 kg) fixa como meta 96 % entregues em 1–2 dias úteis em Itália e em 2–10 dias úteis no estrangeiro.

### Quem entrega em Portugal uma encomenda enviada pela BRT?

Normalmente a [DPD Portugal](carrier:dpd-pt), com código DPD de 14 dígitos; num envio de volume único em que a BRT indique esse código, o Peek passa automaticamente para a DPD Portugal assim que aí surja uma leitura datada atualizada.

:::sources
- [BRT: Página inicial (EN)](https://www.brt.it/en/) – pesquisa por BRTcode
- [BRT: FAQ (EN)](https://www.brt.it/en/customer-service/faq/) – BRTcode, ausência, armazenagem, prazos, danos e 20 kg
- [BRT: Codes and Support (EN)](https://www.brt.it/en/customer-service/codes-and-support/) – onde consta o BRTcode
- [BRT: Alerta de phishing (EN)](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – aviso de 16 de abril de 2026
- [BRT: Código de segurança (IT)](https://www.brt.it/it/faq/cose-il-codice-di-sicurezza/) – código de 3 dígitos
- [BRT: Rintraccia spedizione (EN)](https://vas.brt.it/vas/sped_numspe_par.htm?lang=en) – formatos, 12 e 2 meses e idiomas
- [BRT: Ajuda do detalhe (EN)](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – filiais, armazenagem, encargos e DPD
- [BRT: BRT-fermopoint (EN)](https://www.brt.it/en/brt-fermopoint/) – lojas, fins de semana e 10 dias
- [BRT: Locker (EN)](https://www.brt.it/en/network/locker/) – cacifos 24/7, QR, PIN e 20 kg
- [BRT: Levantamento em filial (EN)](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – terceiro autorizado e documentos
- [BRT: Support (EN)](https://services.brt.it/en/support) – formulários sem telefone
- [BRT: Pesquisa de filiais (EN)](https://services.brt.it/en/customer-service/search-for-a-branch) – filiais BRT
- [BRT: Carta dei servizi, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – julho de 2026: 31,5 kg, tentativas, 10 dias, 8/7 dias, 96 %, 800 278 708, assistenzaclienti@brt.it e 45 dias
- [BRT: Carta dei servizi (IT)](https://www.brt.it/it/carta-dei-servizi/) – form-agcom, conciliação e AGCOM
- [BRT: Modulo di Reclamo, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – reclamação de destinatário ou remetente
- [BRT: Modulo di Conciliazione, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – conciliazioni@brt.it e quota
- [BRT: Condizioni generali, PDF (IT)](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – dias acordados com o remetente
- [DPD: Página inicial (EN)](https://www.dpd.com/en/) – marcas Geopost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos, estados, fusos e intervalos do Peek
:::
