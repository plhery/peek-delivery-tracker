---
title: Rastreio SkyPostal: códigos SKAMZUS, SP e apoio
description: Rastreia uma encomenda SkyPostal dos EUA para a América Latina: códigos SKAMZUS e SP...BR, estados DTP, CEN e HTI, alfândega e emails de apoio por país.
slug: rastreio-skypostal
published: 2026-11-24
updated: 2026-11-27
---

A **SkyPostal** é uma rede de entregas domésticas nos Estados Unidos e transfronteiriças para a América Latina com sede em Miami, fundada por Albert P. e A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). A partir do seu hub de exportação em Miami, Florida, transporta encomendas de lojas como Amazon, Temu e eBay para o Brasil, México, Colômbia, Peru, Equador, Chile e Costa Rica, assegurando o desalfandegamento e entregando a distribuição final a parceiros locais. Consulta o teu código `SKAMZUS` ou `SP...BR` em [tracking.skypostal.com](https://tracking.skypostal.com/) ou cola-o na caixa acima para ver os estados aduaneiros (`CEN`, `HTI`, `DTP`) e os contactos de apoio.

## Números de rastreio SkyPostal (formatos `SKAMZUS` e `SP...BR`)

A SkyPostal utiliza vários formatos transfronteiriços consoante o vendedor de origem e o país de destino, mais frequentemente códigos `SKAMZUS` de 17 caracteres (muito usados em exportações da Amazon EUA para a América Latina) e códigos `SP...BR` de 15 caracteres para o Brasil:

| Formato (inventado) | O que é |
| --- | --- |
| `SKAMZUS0482917365` | Código SkyPostal de 17 caracteres para exportações Amazon EUA (`SKAMZUS` + 10 dígitos). |
| `SP04829173605BR` | Código SkyPostal transfronteiriço de 15 caracteres para o Brasil (`SP` + 11 dígitos + `BR`). |

Quando selecionado manualmente, o rastreio da SkyPostal também pesquisa outras guias de vendedores e referências de parceiros alfanuméricas de 8 a 40 caracteres com pelo menos um dígito. Vê o guia de [formatos de números de seguimento](guide:tracking-number-formats).

## O que significam os estados de rastreio da SkyPostal

A SkyPostal associa um código de evento de 3 letras a cada ponto de controlo — desde o pré-desalfandegamento eletrónico e entrada no hub de Miami até voos, alfândega de destino e registos retransmitidos pela transportadora local:

| Código e texto SkyPostal | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Dados da encomenda, validação do código pautal HS e número fiscal do comprador recebidos antes da chegada ao hub SkyPostal. | «Etiqueta criada» |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Encomenda física recebida no centro de exportação SkyPostal em Miami. | «Aceite pela transportadora» |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Processada em Miami, embarcada por via aérea e transferida da companhia aérea para o operador terrestre no país de destino (não é entrega ao domicílio). | «Em trânsito» |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Entrou em desalfandegamento no destino ou aguarda libertação no armazém alfandegado do aeroporto. | «Na alfândega» |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Desalfandegada, entregue à transportadora local latino-americana e a circular em hubs locais. | «Em trânsito» |
| `OFD` (`OUT FOR DELIVERY`) | Em distribuição hoje com o estafeta parceiro no destino. | «Em distribuição» |
| `PIK` (`AVAILABLE FOR PICKUP`) | Pronta para levantamento pelo destinatário num ponto local ou estação postal. | «Pronta para levantamento» |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Tentativa de entrega falhada por não haver ninguém disponível para receber. | «Tentativa de entrega» |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Retida por problema com o número fiscal, morada incorreta ou incompleta, etiqueta rejeitada ou recusa de entrega. | «Problema reportado» |
| `DEL` (`DELIVERED`) | Entregue ao destinatário pelo parceiro de destino. | «Entregue» |

Nota que `DTP` (*Delivered from airline to operator*) significa apenas que a companhia aérea entregou a mala postal aérea ao operador de terra no aeroporto de destino — o Peek mantém `DTP` em «Em trânsito» em vez de marcar como entregue. Eventos internos de faturação (`CUT`, `PAY`, `FSC`) ficam sem etapa.

## Alfândega na América Latina, números fiscais (`HTI`) e entrega local

Os envios transfronteiriços dos Estados Unidos para a América Latina através da SkyPostal exigem conformidade rigorosa com dados aduaneiros e fiscais:

- **Número fiscal do comprador (`TIC` / `HTI`):** os registos da SkyPostal assinalam quando o número fiscal é recolhido (`TAX ID COLLECTED`) e quando uma encomenda fica retida por causa dele (`HOLD FOR PROBLEMATIC TAX ID`). Se aparecer `HTI`, pergunta ao vendedor ou envia email para o endereço de apoio SkyPostal do teu país com o número de rastreio completo. Consulta o guia de [encomendas retidas na alfândega](guide:customs).
- **Entrega local de última milha:** assim que aparecem `CCH` (*Customs cleared*) e `TTC` (*Tendered to local carrier*), uma transportadora local entrega a encomenda e o rastreio SkyPostal apresenta os seus registos com a cidade do parceiro.

## Encomenda SkyPostal atrasada, retida ou desaparecida

- **Pausas silenciosas entre Miami e a alfândega de destino:** o rastreio pára frequentemente entre `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) e `CCH` (`CUSTOMS CLEARED`) enquanto cargas consolidadas passam por inspeção aduaneira. Vê [quando o rastreio deixa de atualizar](guide:tracking-not-updating).
- **Erros de morada (`ICA`) ou entregas recusadas (`DRF`):** contacta primeiro o teu marketplace (Amazon, Temu, eBay, etc.) para reenvio ou reembolso e escreve para o email de apoio SkyPostal do teu país com o número completo se continuar retida localmente. Vê também [entregue mas não recebida](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente SkyPostal e emails por país

Em [SkyPostal Customer Service](https://www.skypostal.com/customer-service), a SkyPostal pede aos destinatários finais que contactem a equipa **por email**, indicando sempre o número completo de seguimento e os detalhes do pedido (tempo de resposta publicado: **3–5 dias úteis**):

- **Brasil:** `Brazilcs@skypostal.com`
- **México:** `Mexicocs@skypostal.com`
- **Colômbia:** `Colombiacs@skypostal.com`
- **Peru:** `Perucs@skypostal.com`
- **Equador:** `Ecuadorcs@skypostal.com`
- **Todos os outros países:** `customerservice@skypostal.com`
- **Sede nos EUA:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Estados Unidos ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Desconfia de mensagens falsas por WhatsApp ou SMS a exigir pagamento urgente de impostos num site desconhecido para libertar uma importação para a América Latina. Confirma sempre qualquer imposto real na página da encomenda ou no portal oficial da alfândega ou dos correios nacionais. Consulta [mensagens SMS fraudulentas de entrega](guide:delivery-scam-texts).

## Acompanhar uma encomenda SkyPostal com o Peek

O [Peek](/) deteta automaticamente a SkyPostal como único candidato quando introduzes um número `SKAMZUS` (`SKAMZUS0482917365`, inventado) ou `SP...BR` (`SP04829173605BR`, inventado) e mostra «SkyPostal tem esta encomenda.» assim que o rastreio da SkyPostal devolve histórico; o Peek também reconhece ligações coladas `tracking.skypostal.com/?trck_number=` e `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Para outras guias de lojas, toca em «Alterar» ao adicionar ou «Alterar transportadora» no ecrã da encomenda e seleciona **SkyPostal** (pesquisar «Sky Postal» também encontra).

O Peek lê o rastreio da SkyPostal e apresenta a etapa («Etiqueta criada», «Aceite pela transportadora», «Em trânsito», «Na alfândega», «Em distribuição», «Pronta para levantamento», «Tentativa de entrega», «Problema reportado», «Entregue»), o texto em inglês do registo e a cidade do parceiro ou, na falta dela, o código aeroportuário SkyPostal. Os códigos de 3 letras determinam a etapa e nenhuma aplicação Peek os mostra. A SkyPostal fornece cada registo sem desfasamento em relação a UTC: o Peek data-o no fuso horário do local (hora de Nova Iorque para `MIA`, São Paulo para `GRU`, Bogotá ou Lima para registos nessas cidades), um registo sem local no fuso do registo vizinho, e mostra tudo na hora do teu dispositivo. Os registos de um parceiro podem chegar duas vezes de fontes separadas por horas, pelo que uma etapa anterior listada após uma entrega não a anula. O Peek não guarda comprovativo de entrega, comentários nem o número próprio do parceiro, e nenhuma aplicação Peek mostra quem assinou. O rastreio SkyPostal não fornece dia estimado de entrega, peso nem hora de entrega separada.

Enquanto tens a encomenda aberta no Peek, ou no dia da entrega com as notificações ativadas, o Peek consulta até de 10 em 10 minutos; no dia em que a SkyPostal apresenta `OFD` (`OUT FOR DELIVERY`), de 2 em 2 minutos a partir das 6h00 no relógio do registo mais recente enquanto o rastreio da SkyPostal responder. Caso contrário, o Peek verifica de hora a hora. Após 12 horas apenas em «Etiqueta criada» ou «Aceite pela transportadora», ou dois dias sem novidades, verifica de hora a hora independentemente da forma como acompanhas a encomenda, e de 6 em 6 horas após sete dias em «Etiqueta criada». Quando o rastreio da SkyPostal não responde e o Peek acompanha a encomenda apenas através de serviços de rastreio, verifica de hora a hora, de 3 em 3 horas após um dia sem novidades e até de 15 em 15 minutos no dia em que a encomenda está em distribuição, enquanto tiveres a encomenda aberta ou as notificações ativadas.

Quando o registo mais recente tem quatro dias e a encomenda continua em «Etiqueta criada», «Aceite pela transportadora» ou «Em trânsito», o Peek assinala-a com o dia desse registo, como «Sem atualizações desde seg 12 out». A página da encomenda apresenta então «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a SkyPostal.», com a ligação «Pede ajuda a SkyPostal» para a página de apoio ao cliente da SkyPostal. Passadas três semanas sem novos registos, mostra «Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, contacta o vendedor. O Peek continua a perguntar a SkyPostal.»

Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek acompanha apenas as atualizações de rastreio e não pode submeter números fiscais, desalfandegar na América Latina nem contactar a SkyPostal por ti.

## Perguntas sobre o rastreio SkyPostal

### Como é um número de rastreio SkyPostal?

Os códigos SkyPostal mais frequentes são números `SKAMZUS` de 17 caracteres (`SKAMZUS0482917365`, inventado) usados em exportações Amazon EUA e códigos `SP...BR` de 15 caracteres (`SP04829173605BR`, inventado) para o Brasil. O rastreio também aceita outras referências comerciais alfanuméricas de 8 a 40 caracteres com pelo menos um dígito.

### O que significa `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) na SkyPostal?

Não significa que a encomenda foi entregue na tua morada. `DTP` indica que a companhia aérea entregou a carga ao operador de terra no aeroporto de destino antes da entrada na alfândega (`CEN`) e da entrega à transportadora local (`TTC`).

### O que significa `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

A alfândega de destino assinalou um número fiscal em falta ou incorreto do destinatário. Confirma os teus dados com a loja e envia o número completo de seguimento para o email de apoio SkyPostal do teu país em [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Porque o rastreio SkyPostal parou entre Miami e a entrega?

É habitual existirem pausas entre `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) e `CCH` (`CUSTOMS CLEARED`) enquanto cargas consolidadas passam pela inspeção aduaneira. Após `CCH` e `TTC` (`TENDERED TO LOCAL CARRIER`), o parceiro local assume a distribuição final.

### Como contactar o apoio ao cliente da SkyPostal por país?

Escreve com o número completo de seguimento para `Brazilcs@skypostal.com` (Brasil), `Mexicocs@skypostal.com` (México), `Colombiacs@skypostal.com` (Colômbia), `Perucs@skypostal.com` (Peru), `Ecuadorcs@skypostal.com` (Equador) ou `customerservice@skypostal.com` (restantes países); o prazo de resposta publicado é de 3–5 dias úteis.

:::sources
- [SkyPostal: Official Website](https://www.skypostal.com/) – envios domésticos EUA e transfronteiriços para a América Latina, sede em Miami FL
- [SkyPostal: About Us](https://www.skypostal.com/about-us) – história da empresa e rede privada de distribuição na América Latina
- [SkyPostal: Customer Service](https://www.skypostal.com/customer-service) – emails de apoio por país (Brasil, México, Colômbia, Peru, Equador, geral), nota sobre Amazon/Temu/eBay e prazo de resposta de 3–5 dias úteis (página de ajuda ligada pelo Peek)
- [SkyPostal: Tracking Portal](https://tracking.skypostal.com/) – portal público de rastreio individual
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – regras de deteção `SKAMZUS` e `SP...BR`, regras de ligação `tracking.skypostal.com`, mapa de códigos de 3 letras (`DRC`–`DEL`, `DTP`, `HTI`) e fusos horários dos registos
:::
