---
title: Rastreamento SkyPostal: códigos SP...BR, status e suporte
description: Rastreie encomendas SkyPostal dos EUA para o Brasil e América Latina: códigos SKAMZUS e SP...BR, status de Miami e alfândega (CEN, HTI, DEL) e e-mails.
slug: rastreamento-skypostal
published: 2026-11-24
updated: 2026-11-24
---

A **SkyPostal** é uma rede de entregas domésticas nos Estados Unidos e remessas internacionais para a América Latina, sediada em Miami e fundada por Albert P. e A.J. Hernandez ([skypostal.com](https://www.skypostal.com/)). Operando a partir de seu principal centro de exportação em Miami, na Flórida, a SkyPostal transporta encomendas de comércio eletrônico para grandes varejistas e marketplaces — incluindo Amazon, Temu e eBay — com destino ao Brasil, México, Colômbia, Peru, Equador, Chile e Costa Rica, cuidando do desembaraço de exportação e importação e repassando a entrega final no endereço a parceiros regionais latino-americanos. Você pode consultar um envio em [tracking.skypostal.com](https://tracking.skypostal.com/) ou colar seu código ou link da SkyPostal na caixa acima.

## Códigos de rastreamento SkyPostal (formatos `SKAMZUS` e `SP...BR`)

A SkyPostal emite vários formatos de rastreamento internacional conforme a loja de origem e o país de destino, sendo os mais comuns os códigos `SKAMZUS` de 17 caracteres (muito usados em exportações da Amazon EUA para a América Latina) e os códigos `SP...BR` de 15 caracteres para o Brasil:

| Formato (inventado) | O que é |
| --- | --- |
| `SKAMZUS0482917365` | Código SkyPostal de 17 caracteres para exportação Amazon EUA (`SKAMZUS` + 10 dígitos). |
| `SP04829173605BR` | Código SkyPostal de 15 caracteres para encomendas internacionais ao Brasil (`SP` + 11 dígitos + `BR`). |

Quando selecionada manualmente, a consulta da SkyPostal também aceita outros códigos alfanuméricos de lojistas e parceiros com 8 a 40 caracteres contendo pelo menos um dígito. Veja [formatos de códigos de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da SkyPostal

A SkyPostal vincula um código de evento de 3 letras a cada etapa — cobrindo pré-registro eletrônico, entrada no centro de Miami, voos, alfândega no destino e leituras repassadas pela transportadora local:

| Código e texto da SkyPostal | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `DRC` (`DATA RECEIVED`), `HCV` (`HS_CODE VALIDATION`), `TIC` (`TAX ID COLLECTED`), `DCO` (`DOCUMENTATION COMPLETE`) | Dados do pedido, validação do código HS e CPF/documento fiscal recebidos antes de o pacote chegar ao centro da SkyPostal. | "Etiqueta criada" |
| `RUT` (`RECEPTACLE RECEIVED IN US TRANSPORT FACILITY`), `REC` (`RECEIVED`) | Encomenda física recebida no centro de exportação da SkyPostal em Miami. | "Recebida pela transportadora" |
| `XRS` (`X-RAY SCAN`), `PLP` (`PROVIDER LABEL PRINTED`), `INC` (`NESTED TO RECEPTACLE`), `PRO`, `MNF`, `FDP` (`FLIGHT DEPARTED`), `ATT`, `ATC` (`ARRIVED IN COUNTRY`), `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) | Processada em Miami, embarcada em voo e entregue pela companhia aérea ao operador de solo no país de destino (ainda não é entrega ao destinatário). | "Em trânsito" |
| `CEN` (`CUSTOMS ENTRY`), `CHE` (`CUSTOMS HOLD AT CONTROL ENTITY`) | Entrou na fiscalização aduaneira de destino ou aguarda liberação no armazém alfandegado do aeroporto. | "Na alfândega" |
| `CCH` (`CUSTOMS CLEARED`), `TTC` (`TENDERED TO LOCAL CARRIER`), `TDP`, `PES` (`PROVIDER ENTRY SCAN`), `INT`, `PRH`, `PDH`, `SLH` (`HOLD RESOLVED`) | Liberada pela alfândega, entregue à transportadora local e em trânsito pelas unidades regionais. | "Em trânsito" |
| `OFD` (`OUT FOR DELIVERY`) | Saiu com o entregador da parceira local para entrega hoje. | "Saiu para entrega" |
| `PIK` (`AVAILABLE FOR PICKUP`) | Pronta para retirada em um ponto de coleta ou agência postal local. | "Pronta para retirada" |
| `CNA` (`CONSIGNEE NOT AVAILABLE`) | Tentativa de entrega sem sucesso porque não havia ninguém disponível para receber. | "Tentativa de entrega" |
| `HTI` (`HOLD FOR PROBLEMATIC TAX ID`), `ICA` (`INCORRECT / INCOMPLETE ADDRESS`), `DRF` (`DELIVERY REFUSED`), `PLR` (`PROVIDER LABEL REJECTED`), `INH` (`ON HOLD`) | Retida por problema no CPF/documento fiscal, endereço incorreto ou incompleto, etiqueta rejeitada ou recusa. | "Precisa de atenção" |
| `DEL` (`DELIVERED`) | Entregue ao destinatário pela parceira local. | "Entregue" |

Observe que `DTP` (*Delivered from airline to operator*) significa apenas que a companhia aérea repassou o lote aéreo ao operador de solo no aeroporto de destino — o Peek mantém `DTP` em "Em trânsito", sem marcar como entregue. Eventos internos de faturamento (`CUT`, `PAY`, `FSC`) não alteram a etapa.

## Alfândega na América Latina, CPF/documento fiscal (`HTI`) e entrega local

As remessas internacionais enviadas dos Estados Unidos para o Brasil e demais países latino-americanos pela SkyPostal exigem conformidade rigorosa com dados aduaneiros e fiscais:

- **CPF / documento fiscal do comprador (`TIC` / `HTI`):** as leituras da SkyPostal registram quando o documento fiscal é coletado (`TAX ID COLLECTED`) e quando o pacote fica retido por divergência (`HOLD FOR PROBLEMATIC TAX ID`). Se aparecer `HTI`, procure o vendedor ou envie um e-mail ao atendimento da SkyPostal do seu país informando o código de rastreamento completo. Veja [encomendas retidas na alfândega](guide:customs).
- **Entrega local de última milha:** assim que `CCH` (*Customs cleared*) e `TTC` (*Tendered to local carrier*) aparecem, uma transportadora local assume a entrega, e o rastreador da SkyPostal lista as movimentações com a cidade do parceiro.

## Encomenda SkyPostal atrasada, retida ou extraviada

- **Intervalos sem leitura entre Miami e a alfândega de destino:** é comum o rastreamento pausar entre `FDP` (`FLIGHT DEPARTED`), `ATC` (`ARRIVED IN COUNTRY`) e `CCH` (`CUSTOMS CLEARED`) enquanto lotes consolidados passam pela fiscalização aduaneira. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).
- **Erro de endereço (`ICA`) ou entrega recusada (`DRF`):** contate primeiro o marketplace (Amazon, Temu, eBay etc.) para reenvio ou reembolso e escreva ao e-mail regional da SkyPostal com seu código completo se o pacote ainda estiver retido localmente. Veja [entregue, mas não recebido](guide:delivered-not-received), [encomenda perdida](guide:lost-parcel), [encomenda danificada](guide:damaged-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento SkyPostal e e-mails de suporte por país

Na página [Atendimento ao Cliente SkyPostal](https://www.skypostal.com/customer-service), a SkyPostal orienta os destinatários finais a entrarem em contato **por e-mail**, sempre informando o código de rastreamento completo e os detalhes da solicitação (prazo de resposta divulgado: **3 a 5 dias úteis**):

- **Brasil:** `Brazilcs@skypostal.com`
- **México:** `Mexicocs@skypostal.com`
- **Colômbia:** `Colombiacs@skypostal.com`
- **Peru:** `Perucs@skypostal.com`
- **Equador:** `Ecuadorcs@skypostal.com`
- **Demais países:** `customerservice@skypostal.com`
- **Sede nos EUA:** *SkyPostal*, 2990 NW 75th Ave, Miami, FL 33122, Estados Unidos ([skypostal.com/about-us](https://www.skypostal.com/about-us)).

> Desconfie de mensagens falsas por WhatsApp ou SMS cobrando taxa urgente em sites desconhecidos para liberar importações na alfândega. Verifique qualquer tributo legítimo diretamente na página do pedido no marketplace ou no portal oficial da alfândega ou dos correios. Veja [golpes por SMS de entrega](guide:delivery-scam-texts).

## Rastrear uma encomenda SkyPostal com o Peek

O [Peek](/) identifica a SkyPostal automaticamente como única candidata quando você digita um código `SKAMZUS` (`SKAMZUS0482917365`, inventado) ou `SP...BR` (`SP04829173605BR`, inventado) e exibe "SkyPostal está com esta encomenda." assim que o rastreador da SkyPostal retorna histórico; o Peek também reconhece links colados de `tracking.skypostal.com/?trck_number=` e `tracking.skypostal.com/single-tracking.aspx?trck_number=`. Para outros códigos de lojistas, toque em "Trocar" ao adicionar ou em "Trocar transportadora" na tela da encomenda e selecione **SkyPostal** (buscar por "Sky Postal" também funciona).

O Peek lê o rastreador da SkyPostal e exibe a etapa ("Etiqueta criada", "Recebida pela transportadora", "Em trânsito", "Na alfândega", "Saiu para entrega", "Pronta para retirada", "Tentativa de entrega", "Precisa de atenção", "Entregue"), o texto da leitura em inglês e a cidade da parceira local ou a sigla do aeroporto da SkyPostal. Os códigos de 3 letras definem a etapa e não aparecem nos apps do Peek. Como a SkyPostal fornece horários sem fuso, o Peek associa cada leitura ao fuso horário do local (Nova York para `MIA`, São Paulo para `GRU`, Bogotá ou Lima para leituras nessas cidades), usa o fuso da leitura vizinha quando não há local informado e exibe tudo no relógio do seu dispositivo. As leituras de parceiros podem chegar em duplicidade de fluxos com horas de diferença, de modo que uma etapa anterior listada depois da entrega não a desfaz. O Peek não armazena comprovante de entrega, comentários nem o código interno da parceira, e nenhum app do Peek mostra quem assinou pelo pacote. O rastreador da SkyPostal não informa data prevista de entrega, peso nem hora separada de entrega.

Enquanto você mantém a encomenda aberta no Peek, ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos; no dia em que a SkyPostal exibe `OFD` (`OUT FOR DELIVERY`), a cada 2 minutos a partir das 6h no relógio da leitura mais recente, enquanto o rastreador da SkyPostal responde. Fora disso, checa a cada hora. Após 12 horas apenas em "Etiqueta criada" ou "Recebida pela transportadora", ou dois dias sem novidades, checa a cada hora independentemente de como você acompanha, e a cada 6 horas após sete dias em "Etiqueta criada". Quando o rastreador da SkyPostal não responde e o Peek acompanha o pacote apenas por serviços de rastreamento, verifica a cada hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados.

Quando a leitura mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek destaca a data dessa leitura, como "Sem atualizações desde seg., 12 de out.". A tela da encomenda então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando SkyPostal.", com o link "Pedir ajuda a SkyPostal" para a página de atendimento da empresa. Após três semanas sem novas leituras, aparece "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando SkyPostal."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha o rastreamento e não pode enviar CPF, liberar encomendas na alfândega nem contatar a SkyPostal em seu nome.

## Perguntas frequentes sobre o rastreamento SkyPostal

### Como é um código de rastreamento da SkyPostal?

Os códigos mais comuns da SkyPostal são `SKAMZUS` de 17 caracteres (`SKAMZUS0482917365`, inventado) em exportações da Amazon EUA e `SP...BR` de 15 caracteres (`SP04829173605BR`, inventado) para o Brasil, embora a SkyPostal também aceite outras referências de lojistas com 8 a 40 caracteres.

### O que significa `DTP` (`DELIVERED FROM AIRLINE TO OPERATOR`) na SkyPostal?

Não significa entrega na sua casa. `DTP` indica apenas que a companhia aérea entregou o lote consolidado ao operador de solo do aeroporto de destino, antes do desembaraço aduaneiro (`CEN`) e do repasse à transportadora local (`TTC`).

### O que significa `HTI` (`HOLD FOR PROBLEMATIC TAX ID`)?

A alfândega de destino sinalizou ausência ou divergência no documento fiscal do destinatário (como o CPF no Brasil ou o RFC no México). Fale com a loja e envie e-mail ao endereço regional da SkyPostal listado em [skypostal.com/customer-service](https://www.skypostal.com/customer-service).

### Como entrar em contato com o suporte da SkyPostal?

Envie um e-mail para a central do seu país (`Brazilcs@`, `Mexicocs@`, `Colombiacs@`, `Perucs@`, `Ecuadorcs@` ou `customerservice@skypostal.com`) com o código de rastreamento completo; a SkyPostal informa prazo de resposta de 3 a 5 dias úteis.

:::sources
- [SkyPostal: Site oficial](https://www.skypostal.com/) – entregas domésticas nos EUA e remessas internacionais para a América Latina, sede em Miami (FL)
- [SkyPostal: Sobre nós](https://www.skypostal.com/about-us) – histórico da empresa e rede privada de entregas na América Latina
- [SkyPostal: Atendimento ao Cliente](https://www.skypostal.com/customer-service) – e-mails de suporte por país (Brasil, México, Colômbia, Peru, Equador e geral), menção a Amazon/Temu/eBay e resposta em 3–5 dias úteis (URL de ajuda vinculada pelo Peek)
- [SkyPostal: Portal de rastreamento](https://tracking.skypostal.com/) – consulta pública individual de encomendas
- [Universal Parcel Scraper: skypostal](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/skypostal) – regras de detecção `SKAMZUS` e `SP...BR`, regras de link `tracking.skypostal.com`, mapa de códigos de 3 letras (`DRC`–`DEL`, `DTP`, `HTI`) e fusos das leituras
:::
