---
title: Rastreamento GLS Polônia: status, GLS Points e lockers
description: Rastreie encomendas da GLS Polônia: códigos de 11/12 dígitos e Track-ID, código postal opcional, status, GLS Points, lockers ORLEN e contato.
slug: gls-poland-tracking
published: 2026-11-26
updated: 2026-11-26
---

A GLS Polônia (GLS Poland, *General Logistics Systems Poland Sp. z o.o.*, sediada em Głuchowo perto de Poznań, com centro de triagem central em Stryków perto de Łódź) opera cerca de 50 filiais e mais de 26.000 pontos de retirada fora de casa pela Polônia — incluindo mais de 18.000 GLS Points (entre eles todas as 11.000 lojas de conveniência Żabka) e cerca de 8.000 lockers 24 horas compartilhados da [ORLEN Paczka](carrier:orlen-paczka). Acompanhe seu envio na [página de rastreamento da GLS Polônia](https://gls-group.com/PL/en/parcel-tracking/) usando o número da encomenda ou o Track-ID, ou cole o código na caixa acima.

## Formatos de número da encomenda e Track-ID da GLS Polônia

Encontre o código no e-mail de envio da loja virtual, no alerta por e-mail/SMS da GLS ou no aviso de entrega não realizada (*awizo*). A caixa de busca da GLS também aceita o Track-ID dos avisos.

| Formato (inventado) | O que é |
| --- | --- |
| `48291736405` | Número GLS de 11 dígitos. Também usado por [GLS Alemanha](carrier:gls-de), [GLS França](carrier:gls-fr), GLS Suíça, [GLS Áustria](carrier:gls-at) e [PPL](carrier:ppl). |
| `482917364052` | Número de 12 dígitos com dígito verificador no final. Também usado por [BRT](carrier:brt), [Colis Privé](carrier:colis-prive) e outras. |

Na [página de rastreamento da GLS Polônia](https://gls-group.com/PL/en/parcel-tracking/), informar o código postal do destinatário libera o histórico completo de movimentações e as opções de alteração da entrega. Para outros formatos, consulte [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da GLS Polônia

A GLS Polônia exibe descrições em polonês no site em polonês e descrições em inglês do grupo GLS pela API internacional lida pelo Peek.

| A GLS Polônia / Grupo GLS diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Preadvice`, `Nadawca nadał numer paczce - paczka nie została jeszcze przekazana kurierowi GLS` | O remetente gerou a etiqueta eletronicamente; a GLS ainda não recebeu o pacote físico. | "Etiqueta criada" |
| `Paczka została przekazana GLS` | A GLS coletou ou recebeu a encomenda em sua rede. | "Recebida pela transportadora" |
| `In transit`, `Final parcel center`, `Paczka zarejestrowana w filii GLS`, `Paczka wyjechała z filii GLS` | Seguindo pelos centros de triagem e filiais locais da GLS na Polônia rumo ao depósito de entrega. | "Em trânsito" |
| `In delivery`, `Paczka w doręczeniu` | O entregador carregou o pacote para entrega hoje. | "Saiu para entrega" |
| Chegada a um GLS Point ou locker, que a GLS marca como entregue | Aguardando retirada em um GLS Point ou locker 24 horas. | "Pronta para retirada" |
| `The parcel has been delivered.`, `Paczka została doręczona`, `Handing over the parcel to the recipient at the GLS ParcelShop.` | Entregue a você, pessoa autorizada ou vizinho, ou retirada em um GLS Point. | "Entregue" |
| `Paczka niedoreczona - odmowa przyjecia przez Odbiorce` | O destinatário recusou a encomenda. | "Tentativa de entrega" |
| `Paczka pozostawiona w filii doreczajacej - Odbiorca odmowil przyjecia` | Recusada e retida na filial de entrega. | "Precisa de atenção" |

No dia da entrega, o [Live Tracking](https://gls-group.com/PL/en/live-tracking/) da GLS Polônia envia por e-mail um link com horário previsto em janela de 15 minutos e mostra o entregador no mapa quando faltam 20 paradas ou menos. A barra de progresso da GLS marca um pacote deixado em GLS Point ou locker como entregue enquanto aguarda você; o Peek mostra essa etapa como "Pronta para retirada" e muda para "Entregue" depois da retirada.

## Prazos da GLS Polônia, GLS Points, lockers ORLEN e FlexDeliveryService

**Dias e velocidade de entrega.** A GLS Polônia entrega de segunda a sexta-feira (entrega aos sábados apenas quando contratada especificamente pelo remetente). Envios domésticos padrão costumam chegar no próximo dia útil (sem garantia; remetentes podem contratar Express 10:00 ou 12:00). A partir da Polônia, a GLS informa prazo de 2 a 7 dias úteis para outros países da UE, conforme o destino ([GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/)).

**Peso, dimensões e CashService (contra reembolso).** Encomendas domésticas em domicílio podem pesar até 31,5 kg (40 kg em entregas internacionais no endereço; 31,5 kg em GLS Points selecionados; 20 kg nos demais GLS Points, lockers e myGLS), dentro de 200 × 80 × 60 cm e soma de comprimento mais contorno de até 300 cm. O pagamento contra reembolso (`CashService`, até 6.000 PLN por dia) pode ser feito ao entregador em dinheiro, cartão ou BLIK.

**Entrega não realizada e segunda tentativa.** Segundo a [página de recebimento da GLS Polônia](https://gls-group.com/PL/en/receiving/), se você não estiver em casa, o entregador pode deixar o pacote com um vizinho (com seu consentimento) ou redirecionar uma encomenda elegível sem contra reembolso para um GLS Point próximo. Caso contrário, o volume retorna à filial para segunda tentativa no próximo dia útil; se continuar sem entrega, permanece no depósito antes de voltar ao remetente (5 dias úteis para retirada no depósito via FlexDeliveryService, ou devolução após 6 dias úteis conforme o guia de recebimento).

**Prazo nos GLS Points.** Conforme os [termos](https://gls-group.com/PL/pl/regulamin/) da GLS Polônia para GLS Points e lockers (vigentes desde 9 de novembro de 2026) e seu [FAQ](https://gls-group.com/PL/en/faq/), encomendas ficam guardadas em um GLS Point por **7 dias corridos** antes de voltarem ao remetente (as páginas de [recebimento](https://gls-group.com/PL/en/receiving/) e GLS Points ainda mencionam sete dias úteis). Retire com o PIN recebido por SMS ou e-mail; a GLS pode solicitar documento de identidade.

**Lockers 24 horas ORLEN Paczka.** Encomendas enviadas a [lockers compartilhados ORLEN Paczka](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) aguardam por **72 horas** e retornam automaticamente ao remetente após esse prazo. Abra o compartimento com o PIN por SMS/e-mail, código QR ou desbloqueio remoto no app myGLS — não exige documento, logo qualquer pessoa com o código pode retirar. Suporte técnico 24 horas para lockers: `801 167 536` ou `502 167 536`.

**Redirecionamento FlexDeliveryService.** Pelo link no e-mail de notificação da GLS (sem precisar criar conta), o [redirecionamento de encomendas](https://gls-group.com/PL/en/parcel-redirection/) permite escolher um GLS Point, locker ORLEN, novo endereço, nova data de entrega, retirada no depósito, autorização de local seguro ou recusa da encomenda.

## Alfândega, danos, reclamações e extravios na GLS Polônia

Em compras on-line, o vendedor detém o contrato de transporte com a GLS Polônia e responde por reembolsos ou reenvios.

**Encomendas de fora da UE.** Remessas dentro da UE não passam pela alfândega. Para pedidos on-line vindos de fora da UE, segundo as [regras aduaneiras da Comissão Europeia](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en), incide imposto aduaneiro fixo de €3 por categoria de item em remessas de até €150 (em vigor desde 1º de julho de 2026) e também taxa de manuseio da UE de €2 (em vigor desde 1º de novembro de 2026), além do IVA de importação polonês. Veja [encomendas retidas na alfândega](guide:customs).

**Dano visível e dano oculto.** Você pode recusar uma encomenda visivelmente danificada na entrega para que o entregador registre a ocorrência e a devolva ao remetente. Pelos [Termos de Serviço](https://gls-group.com/PL/pl/regulamin/) da GLS Polônia, danos ocultos descobertos após o recebimento devem ser informados em até **7 dias** (por escrito à sede da GLS ou pelo [formulário de reclamações de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/)). Os prazos gerais de reclamação são de 30 dias a partir da aceitação pela GLS (45 dias para extravio, dentro de 12 meses do despacho), e a GLS responde em até 30 dias. Veja [encomenda danificada](guide:damaged-parcel) e [encomenda extraviada](guide:lost-parcel).

**Rastreamento parado ou entregue sem recebimento.** Se o rastreamento ficar dias parado, consulte [por que o rastreamento para de atualizar](guide:tracking-not-updating). Se aparecer entregue sem estar com você, verifique se aguarda em um GLS Point, pergunte aos vizinhos e fale com a loja; veja [entregue mas não recebida](guide:delivered-not-received) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento da GLS Polônia e alerta contra golpes

Para reembolso de pedidos, contate primeiro a loja virtual. Para falar diretamente com a GLS Polônia (dados de [Contato GLS](https://gls-group.com/PL/en/gls-contact/) e [Informações Corporativas](https://gls-group.com/PL/en/corporate-information/)):

- **Central telefônica:** `+48 46 814 82 20` (segunda a sexta 07:00–19:00, sábado 07:00–15:00).
- **E-mail:** `gls@gls-poland.com` (escritório corporativo `office@gls-poland.com`).
- **Formulários on-line:** [formulário de contato GLS Polônia](https://gls-group.com/PL/en/gls-contact/) e [formulário de reclamações de consumidores](https://gls-group.com/PL/pl/reklamacje-konsumenckie/).
- **Suporte técnico 24/7 para lockers:** `801 167 536` / `502 167 536`.
- **Sede:** General Logistics Systems Poland Sp. z o.o., ul. Tęczowa 10, Głuchowo, 62-052 Komorniki, Polônia.

> **Alerta contra golpes na [página de segurança da GLS Polônia](https://gls-group.com/PL/en/security-advices/):** a GLS nunca envia links por e-mail pedindo pagamento por cartão ou transferência bancária (CashService/ExWorksService são pagos diretamente ao entregador em dinheiro, cartão ou BLIK na entrega) e nunca manda mensagens operacionais de provedores gratuitos como Gmail ou Outlook. Encaminhe mensagens suspeitas para `security@gls-poland.com` (ou `dataprotection@gls-poland.com`) e denuncie links de phishing na Polônia ao CSIRT NASK CERT (`incydent.cert.pl`, `cert@cert.pl` ou SMS `8080`). Veja [golpes de entrega por SMS](guide:delivery-scam-texts).

## Acompanhe uma encomenda da GLS Polônia com o Peek

Cole o código no [Peek](/). Códigos de 11 e 12 dígitos são usados por várias transportadoras, então o Peek consulta as mais prováveis considerando seu país (região do celular ou país de acesso na web). Na Polônia, ele consulta primeiro a GLS Polônia, antes de [GLS França](carrier:gls-fr), [GLS Alemanha](carrier:gls-de), GLS Suíça e outras como [PPL](carrier:ppl) (11 dígitos) ou [BRT](carrier:brt) e [Colis Privé](carrier:colis-prive) (12 dígitos), exibindo normalmente "GLS Poland está com esta encomenda." Em outros países, a GLS Polônia só é consultada depois que as demais transportadoras prováveis não encontram o código. Links colados de `gls-group.com` ou `gls-group.eu` com `/PL/` no caminho e o número em `match=`, `parcelNumber=` ou `matchParcelNumber=` são registrados direto como GLS Poland. Fora isso, clique em "Trocar" ao adicionar ou "Trocar transportadora" em um pacote já salvo e selecione GLS Poland.

O campo "Código postal de entrega" é marcado como "Opcional", mas a GLS só exibe as movimentações com ele, conforme informa a linha sob o campo: "GLS Poland só mostra as movimentações com o código postal de entrega." Sem ele, o Peek obtém o status da GLS sem o histórico detalhado, consulta outros serviços de rastreamento nesse meio-tempo e exibe "Adicionar código postal" na página da encomenda. Com conta, o Peek tenta uma vez o último código postal usado por você em um envio GLS Poland ou, na falta dele, o da sua encomenda mais recente. Se a GLS não localizar o pacote com esse código postal, o Peek avisa e oferece "Alterar código postal". Enquanto a GLS aguarda o código postal ou não localiza o volume com o código informado e nada responde há um dia, o Peek consulta uma vez por dia; adicionar ou alterar o código postal refaz a consulta na hora. O código postal vai apenas para a GLS e, quando necessário, para serviços de rastreamento, nunca aparecendo em links compartilhados.

Com o código postal, você vê o status, cada movimentação no mapa, o dia previsto, o peso, o horário de entrega e, em GLS Points, o nome e endereço do ponto. Seu nome, endereço, dados de contato, assinatura e referência do remetente são descartados. O Peek consulta o serviço em inglês da GLS em vez da página em polonês: assim, `W doręczeniu` aparece como `In delivery` e `Paczka została doręczona` como `The parcel has been delivered.` A chegada a um GLS Point ou locker aparece como "Pronta para retirada". Os horários seguem o fuso da Polônia.

O Peek consulta a GLS cerca de uma vez por hora, inclusive no dia da entrega, e atualizar manualmente não encurta o intervalo (4 horas após uma falha na consulta); depois de sete dias sem novidades em "Etiqueta criada", consulta a cada 6 horas. Somente por serviços de rastreamento, consulta a cada hora e a cada 3 horas após um dia sem novidades.

Quando a movimentação mais recente completa quatro dias e a encomenda continua em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito", o Peek sinaliza com a data desse registro, como "Sem atualizações desde seg 12 out". A página então mostra "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando GLS Poland.", com um link "Pedir ajuda a GLS Poland" para a página de contato. Após três semanas sem novidade, exibe "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor. O Peek continua consultando GLS Poland."

Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek apenas acompanha atualizações de rastreamento e não redireciona encomendas nem abre reclamações junto à GLS Polônia por você.

## Perguntas sobre o rastreamento da GLS Polônia

### Por quanto tempo a GLS Polônia guarda uma encomenda em um GLS Point ou locker ORLEN?

Segundo os [Termos de Serviço](https://gls-group.com/PL/pl/regulamin/) e o [FAQ](https://gls-group.com/PL/en/faq/) atuais da GLS Polônia, os GLS Points guardam encomendas por **7 dias corridos** (as páginas de recebimento e GLS Points ainda falam em sete dias úteis), enquanto os lockers 24 horas ORLEN Paczka guardam por **72 horas** antes da devolução automática ao remetente.

### Por que a GLS Polônia pede meu código postal para mostrar o rastreamento completo?

A GLS protege os detalhes do envio retornando histórico completo, opções de entrega e dados do ParcelShop somente quando o número da encomenda confere com o código postal do destinatário. Informe o código postal de entrega no Peek para receber os dados diretos da GLS.

### Posso pagar uma encomenda CashService (contra reembolso) da GLS Polônia com cartão ou BLIK?

Sim. A GLS Polônia informa que entregas CashService (até 6.000 PLN por dia) podem ser pagas ao entregador na entrega em dinheiro, cartão ou BLIK — nunca por meio de um link de e-mail não solicitado.

### Como redireciono uma encomenda da GLS Polônia para uma loja Żabka ou locker?

Abra o link FlexDeliveryService no e-mail de notificação da GLS (sem precisar de conta) ou acesse [redirecionamento de encomendas](https://gls-group.com/PL/en/parcel-redirection/) para encaminhar o pacote a um GLS Point (incluindo lojas Żabka), um locker ORLEN Paczka, outro endereço ou outra data.

:::sources
- [GLS Poland: Parcel Tracking](https://gls-group.com/PL/en/parcel-tracking/) – número da encomenda, Track-ID e consulta com código postal
- [GLS Poland: About GLS](https://gls-group.com/PL/en/about-gls/) – centro de triagem de Stryków, filiais e visão geral da rede
- [GLS Poland: Corporate Information](https://gls-group.com/PL/en/corporate-information/) – razão social, endereço em Głuchowo e `office@gls-poland.com`
- [GLS Poland: Receiving Parcels](https://gls-group.com/PL/en/receiving/) – entrega em dias úteis, entrega a vizinhos, segunda tentativa, guarda no depósito e CashService
- [GLS Poland: Parcel Redirection](https://gls-group.com/PL/en/parcel-redirection/) – opções do FlexDeliveryService sem conta
- [GLS Poland: GLS Points](https://gls-group.com/PL/en/shipping-receiving-points/gls-points/) – mais de 18.000 GLS Points (incluindo 11.000 lojas Żabka), mais de 26.000 pontos fora de casa, limites de peso e tamanho e prazos para a UE
- [GLS Poland: Parcel Lockers](https://gls-group.com/PL/en/shipping-receiving-points/parcel-lockers/) – cerca de 8.000 lockers 24 horas compartilhados ORLEN Paczka, prazo de retirada de 72 horas, PIN/QR/app myGLS, limite de 20 kg e linha dos lockers
- [GLS Poland: Live Tracking](https://gls-group.com/PL/en/live-tracking/) – mapa e horário previsto com janela de 15 minutos
- [GLS Poland: FAQ](https://gls-group.com/PL/en/faq/) – guarda de 7 dias corridos nos GLS Points, CashService em dinheiro/cartão/BLIK, respostas sobre entregas não realizadas
- [GLS Poland: Terms of Service (Regulamin)](https://gls-group.com/PL/pl/regulamin/) – termos, os de GLS Points e lockers em vigor desde 9 de novembro de 2026: prazo de retirada de 7 dias corridos, aviso de dano oculto em 7 dias, prazos de reclamação de 30/45 dias e resposta em 30 dias
- [GLS Poland: Contact](https://gls-group.com/PL/en/gls-contact/) – central `+48 46 814 82 20` (seg–sex 07:00–19:00, sáb 07:00–15:00), `gls@gls-poland.com` e formulário de contato
- [GLS Poland: Consumer Complaints](https://gls-group.com/PL/pl/reklamacje-konsumenckie/) – envio on-line de reclamações de consumidores
- [GLS Poland: Security Advices](https://gls-group.com/PL/en/security-advices/) – alertas de phishing, `security@gls-poland.com` e denúncia ao CERT NASK
- [GLS Group: Security Advice](https://gls-group.com/GROUP/en/security-advice/) – domínios oficiais do grupo e regras do CashService
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro de €3 até €150 (desde 1º de julho de 2026) e taxa de manuseio da UE de €2 (desde 1º de novembro de 2026)
- [Universal Parcel Scraper: gls-pl](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gls-pl) – detecção de 11/12 dígitos, prioridade na Polônia, código postal opcional, correspondência de status, intervalo de 1 hora/4 horas e link de ajuda
:::
