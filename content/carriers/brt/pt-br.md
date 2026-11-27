---
title: Rastreamento BRT: códigos, status e entregas na Itália
description: Rastreie encomendas BRT na Itália ou vindas de lá: onde achar o BRTcode ou o número de envio, status em inglês, entregas não realizadas, armários e contato.
slug: rastreamento-brt
published: 2026-11-24
updated: 2026-11-24
---

A BRT é uma transportadora expressa italiana, parte do grupo Geopost assim como a DPD. Rastreie uma encomenda pelo link no SMS ou e-mail da BRT, na opção "Where is my shipment?" em [brt.it](https://www.brt.it/en/) ou colando o código na caixa acima. A BRT envia esse número, o BRTcode, quando assume o pacote, caso o remetente tenha informado seus dados de contato.

## Código de rastreamento da BRT e onde encontrar

O BRTcode aparece nos SMS e e-mails da BRT, na etiqueta de envio e no aviso em papel deixado após uma tentativa sem sucesso. Não recebeu mensagem? Veja [onde mais encontrar um código de rastreamento](guide:find-tracking-number).

As próprias páginas da BRT divergem sobre o tamanho, então guie-se pelo formato:

| Formato (fictício) | O que é |
| --- | --- |
| `482917360584` | 12 dígitos: número do envio impresso na etiqueta. |
| `48291736058419` | 14 dígitos: um BRTcode ou número de envio. |
| `482917360584192` | 15 dígitos: ID de volume (parcel ID) colocado pelo remetente. |
| 19 dígitos | Um BRTcode, segundo o formulário de rastreamento da BRT. |

O código de segurança de 3 dígitos da BRT, usado para alterar uma entrega, não é código de rastreamento.

Nada encontrado em brt.it? O número pode estar errado ou ainda não ter entrado na rede da BRT. A [Busca Avançada](https://vas.brt.it/vas/sped_numspe_par.htm) da BRT cobre 12 meses (ou 2 meses para IDs de volume).

Catorze dígitos também combinam com DPD na Alemanha, Suíça e Reino Unido, e doze dígitos combinam com Mondial Relay, Colis Privé e outras empresas; confira qual transportadora consta no e-mail da loja ou no SMS. Veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da BRT

O rastreamento da BRT em vas.brt.it abre em italiano, com opção de alternar para inglês; estes são os textos em inglês. Os cabeçalhos permanecem em italiano: `Data` (data), `Ora` (hora), `Filiale` (filial a consultar) e `Evento` (evento). O myBRT, para onde o campo de brt.it direciona, pode usar outros textos.

| Status da BRT | O que significa para você |
| --- | --- |
| `SHIPPING INFO SENT TO BRT` | A BRT recebeu os dados do remetente, ainda não o pacote: um [status de "etiqueta criada"](guide:tracking-statuses). |
| `COLLECTED` | A BRT coletou o pacote junto ao remetente. |
| `DEPARTED` | Saiu de uma filial ou hub da BRT. |
| `ARRIVED AT DEPOT` | Chegou à filial indicada na coluna `Filiale`. |
| `FOR DELIVERY` | Saiu para entrega com o entregador. |
| `NOTICE OF ATTEMPTED DELIVERY` | Não entregue: ninguém atendeu, e o entregador deixou um aviso. |
| `UNKNOWN/INCOMPLETE CONSIGNEE` | Não entregue: destinatário ou endereço não encontrado. |
| `TURMOIL` | Uma manifestação pública está retendo temporariamente a entrega. |
| `PATRONAL HOLIDAY` | O feriado do padroeiro no destino segura o envio nesse dia antes de chegar à filial entregadora. Não há tentativa de entrega. |
| `DELIVERED` | Entregue. |
| `ARRIVED AT BRT-fermopoint` | Aguardando retirada em um ponto parceiro BRT-fermopoint. |
| `COLLECTED AT BRT-fermopoint` | Retirado no fermopoint. |
| `ARRIVED AT BRT LOCKER` | Aguardando em um armário BRT. |
| `COLLECTED AT BRT LOCKER` | Retirado do armário. |
| `To collect at the parcel shop` | No exterior: a transportadora entregadora deixou o pacote em um ponto parceiro dela. |
| `RETURNED TO SENDER` | Voltando para o remetente. |

`COLLECTED` sozinho refere-se à coleta da BRT junto ao remetente, não à sua retirada. Em `UNKNOWN/INCOMPLETE CONSIGNEE`, "consignee" é você; a BRT lista destinatário desconhecido ou endereço incompleto entre os motivos de retenção (abaixo). Um `DELIVERED` após `RETURNED TO SENDER` indica que o pacote voltou ao remetente.

## Pacote da BRT não entregue: tentativas, fermopoints e armários

Se você não estiver, a BRT deixa aviso por e-mail, SMS ou papel, e a nova tentativa costuma ocorrer no próximo dia útil. A carta de serviços da BRT acrescenta uma segunda tentativa e, se falhar, uma ligação da filial.

Pelo link na mensagem da BRT, pelo app myBRT ou em mybrt.it usando seu BRTcode e código postal ou PIN, talvez você possa escolher outro dia ou faixa de horário, selecionar um BRT-fermopoint ou retirada na filial, liberar um pacote retido ou alterar o endereço se o remetente autorizar.

**Retido ("in giacenza").** Os motivos incluem destinatário ausente ou desconhecido, endereço errado ou incompleto e recusa; o detalhe do rastreamento mostra qual ocorreu. Libere pela mensagem da BRT ou pelo rastreamento. A BRT pede instruções ao remetente; sem instrução por escrito, a carta de serviços prevê retorno 10 dias após o aviso ao remetente, salvo prazo diferente no contrato da loja. Depois que o remetente envia instruções, o rastreamento indica se há taxa de armazenagem para o remetente ou para você; a BRT não publica valores.

**Na filial**, retire pessoalmente ou envie alguém levando documento de identidade próprio, cópia do seu documento e o formulário de procuração da BRT.

**BRT-fermopoint** são comércios parceiros (bancas, tabacarias, papelarias e outros, alguns abertos nos fins de semana) que avisam quando o pacote chega e o guardam por 10 dias.

**Armários BRT** (em lojas, shoppings e postos de combustível, com modelos externos 24 horas) aceitam volumes únicos de até 20 kg: escaneie o QR code da mensagem da BRT ou digite o PIN de 8 dígitos. Em caso de tela com defeito ou gaveta vazia, use o formulário ao final do e-mail de retirada da BRT.

## Pacote da BRT atrasado, extraviado ou danificado

A BRT trata seus prazos de entrega como indicativos. Se o rastreamento parar, fale com a loja (detentora do contrato com a BRT), com a filial indicada no rastreamento ou escolha "The delivery is delayed" na página de [Suporte da BRT](https://services.brt.it/en/support); veja [rastreamento que não atualiza](guide:tracking-not-updating).

Em caso de extravio ou avaria, se você pagou o frete, use o formulário de danos da BRT com fotos; caso contrário, avise o remetente. Na porta, registre avarias visíveis de forma específica no comprovante (a BRT diz que ressalvas genéricas não têm valor) e informe danos ocultos por escrito em até 8 dias corridos após o recebimento em envios dentro da Itália.

Destinatários também podem registrar reclamação formal: anexe o formulário de reclamação em PDF disponível na página [Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) ao formulário em services.brt.it/it/form-agcom. A BRT responde em até 45 dias. Sem resposta satisfatória, segue-se a conciliação por conciliazioni@brt.it com a Casa del Consumatore (exige associação: € 50 por ano ou € 25 para disputas de até € 50) e depois o órgão regulador AGCOM. Para um pacote [marcado como entregue mas não recebido](guide:delivered-not-received), consulte vizinhos e familiares primeiro e use o formulário específico na página de Suporte da BRT antes de partir para a reclamação formal.

## Telefone e atendimento ao cliente da BRT

Estes são os canais da BRT para destinatários:

- **Formulários:** pela página de [Suporte da BRT](https://services.brt.it/en/support), que substituiu o "Contact us": escolha seu caso (atraso, avaria, entregue mas não recebido etc.) para abrir o formulário. A página não exibe telefone.
- **Ligação gratuita:** 800 278 708, de segunda a sexta das 8h30 às 18h30, segundo a carta de serviços italiana da BRT (a empresa não informa se aceita chamadas do exterior).
- **E-mail:** assistenzaclienti@brt.it, também previsto na carta de serviços, informando nome, sobrenome, códigos postais de coleta e entrega e o BRTcode, sob pena de não ser respondido.
- **Chat** em brt.it, ou contato com sua **filial** pelo [localizador de filiais](https://services.brt.it/en/customer-service/search-for-a-branch).

O telefone de Bolonha presente nos documentos da BRT pertence à sede administrativa, não ao atendimento ao cliente.

> Em 16 de abril de 2026, a BRT emitiu alerta sobre SMS e e-mails falsos usando seu nome, como avisos falsos de tentativa de entrega. Confira o remetente e, na dúvida, não clique em links nem abra anexos.

## Como rastrear encomendas da BRT com o Peek

O [Peek](/) reconhece códigos BRT de 12, 14 e 15 dígitos e IDs de volume iniciados por `EOB`. Para IDs `EOB` e a maioria dos códigos de 15 dígitos, a BRT é a única transportadora consultada em qualquer país; para 12 dígitos, o Peek consulta a BRT junto com outras transportadoras compatíveis. Para 14 dígitos, consulta a BRT na hora — ao lado das redes DPD — se seu país for a Itália (região do celular nos apps iPhone e Android, ou país de acesso no navegador); fora da Itália, costuma consultar primeiro as redes DPD e outras empresas, recorrendo à BRT só se nenhuma reconhecer o código. Links do vas.brt.it com `brtCode=` também funcionam; BRTcodes de 19 dígitos e links de mybrt.it não são lidos.

O Peek mostra os status da BRT em inglês, exceto retiradas em armário ou fermopoint, exibidas como "Entregue". Com o Peek em italiano, mostra os textos italianos da BRT e "Consegnata" na entrega; um pacote com um evento que o Peek só conhece em inglês, como um de armário, permanece em inglês. Ele coloca cada local no mapa, exibe o peso e pode notificar mudanças de status, omitindo remetente, destinatário, referência da loja, quantidade de volumes, cubagem e dados do ponto de retirada. A BRT não informa data prevista, mas em códigos de 14 dígitos o Peek pode mostrar a estimativa do rastreamento da DPD (que também lê códigos BRT) ou da Chronopost, se ela levou o pacote à Itália. A previsão some após a data ou antes se a BRT entregar, devolver, deixar em ponto de retirada, registrar tentativa falha ou reportar problema. Os horários da BRT não trazem fuso: o Peek os lê no horário da Itália (ou horário local de filiais no exterior) e mantém a ordem oficial da BRT.

O Peek verifica até a cada 10 minutos e até a cada 2 minutos no dia em que o pacote sai para entrega — a partir das 6h no horário local ou meia hora antes da janela informada pela DPD ou Chronopost — enquanto o rastreador próprio da BRT ou DPD responde (a cada 5 minutos quando a DPD exige navegador); por meio de serviços de rastreamento, até a cada 15 minutos nesse dia, enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidades (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek verifica de hora em hora.

## Perguntas sobre o rastreamento da BRT

### Onde está minha remessa da BRT?

A linha mais recente informa: `ARRIVED AT DEPOT` aponta a filial onde está guardado e `FOR DELIVERY` significa que saiu com o entregador. Ainda sem eventos? Pode não ter chegado à BRT.

### A BRT é a mesma empresa que a DPD?

Não exatamente: ambas são marcas do grupo Geopost. Para encomendas saindo da Itália pela rede DPD, o detalhe de rastreamento da BRT exibe um link para o rastreamento da DPD.

### Quanto tempo a BRT leva para entregar?

Em geral de 24 a 48 horas na Itália, segundo a BRT, somando 12 a 24 horas para Calábria, Sicília, Sardenha e distâncias acima de 800 km; e de 48 a 72 horas para a maioria dos países europeus.

### Onde fica o ponto parceiro BRT mais próximo?

O [buscador de fermopoint](https://www.brt.it/en/brt-fermopoint/) da BRT encontra os comércios BRT-fermopoint mais próximos. Encomendas acima de 20 kg não podem ser enviadas para esses pontos.

:::sources
- [BRT: FAQ](https://www.brt.it/en/customer-service/faq/) – BRTcode, entregas, retenções, prazos e avarias
- [BRT: Codes and Support](https://www.brt.it/en/customer-service/codes-and-support/) – onde localizar o BRTcode
- [BRT: Home](https://www.brt.it/en/) – caixa "Where is my shipment?"
- [BRT: Rintraccia spedizione](https://vas.brt.it/vas/sped_numspe_par.htm) – tamanhos de código e janelas de pesquisa
- [BRT: Shipment detail help](https://vas.brt.it/vas/aiuto/aiuto.hsm?urlaiuto=sped_det_show.htm&lang=en) – retenção, taxas, filiais e integração DPD
- [BRT: Privati](https://www.brt.it/it/privati/) – myBRT e buscador de fermopoint
- [BRT: BRT-fermopoint](https://www.brt.it/en/brt-fermopoint/) – lojas parceiras, fins de semana e prazo de 10 dias
- [BRT: Locker](https://www.brt.it/en/network/locker/) – QR code, PIN e limite de peso
- [BRT: Branch pickup FAQ](https://www.brt.it/en/faq/can-i-pick-up-my-shipment-at-a-brt-branch/) – retirada por terceiros na filial
- [BRT: Support](https://services.brt.it/en/support) – formulários para atrasos, avarias e entregue sem recebimento
- [BRT: Search for a branch](https://services.brt.it/en/customer-service/search-for-a-branch) – busca de filiais BRT
- [BRT: Carta dei servizi PDF](https://www.brt.it/wp-content/uploads/sites/275/2026/11/261105-Carta-dei-Servizi.pdf) – tentativas, retenção, avarias, contatos e prazo de 45 dias
- [BRT: Carta dei servizi](https://www.brt.it/it/carta-dei-servizi/) – reclamações, conciliação e AGCOM
- [BRT: Modulo di Reclamo](https://www.brt.it/wp-content/uploads/sites/275/2026/02/2.-Modulo-Reclamo.pdf) – direito de reclamação do destinatário
- [BRT: Conciliation instructions](https://www.brt.it/wp-content/uploads/sites/275/2026/02/3.-Modulo-di-Conciliazione-con-istruzioni.pdf) – taxa de associação
- [BRT: Condizioni generali](https://www.brt.it/wp-content/uploads/sites/275/2026/01/CONDIZIONI-GENERALI-CONTRATTO-TARIFFARIO_IT_CON-LOGO_REV-31.pdf) – dias de guarda contratados
- [BRT: Alert phishing](https://www.brt.it/en/news/2026/04/16/alert-phishing-sms-email/) – alerta contra mensagens falsas
- [DPD: Home](https://www.dpd.com/en/) – marcas do grupo Geopost
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – o que o Peek lê, as janelas de entrega da DPD e da Chronopost
- [Peek: Routing](https://github.com/plhery/peek-delivery-tracker/blob/main/docs/ROUTING.md) – com que frequência o Peek verifica: até a cada 10 minutos, a cada 2 ou 5 com o pacote saindo para entrega, meia hora antes de uma janela de entrega, de hora em hora por serviços de rastreamento (até a cada 15 minutos com o pacote saindo para entrega), de hora em hora quando nada muda
:::
