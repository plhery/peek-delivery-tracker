---
title: Rastreamento GOFO Italy: códigos GFIT, status e reclamações
description: Rastreie um pacote da GOFO na Itália: formato dos códigos GFIT e CIIT, significado dos status em italiano e como reclamar em caso de atraso.
slug: rastreamento-gofo-italia
published: 2026-11-24
updated: 2026-11-24
---

A GOFO Italy é a rede italiana da GOFO, transportadora de encomendas presente em vários países, responsável por entregas dentro da Itália. A lista de transportadoras do Peek também a conhece como CIRRO Parcel Italy. Seus códigos começam com `GFIT` ou `CIIT`: rastreie na página italiana da GOFO em gofo.com ou cole o código na caixa acima. Ainda sem número? Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Código de rastreamento GOFO: como reconhecer

Estes são os formatos que o Peek identifica como GOFO Italy, em maiúsculas ou minúsculas:

| Formato | Exemplo inventado |
| --- | --- |
| `GFIT` e 13 dígitos | `GFIT4829105736284` |
| `GFIT` e 14 dígitos | `GFIT48291057362848` |
| `CIIT` e 13 dígitos | `CIIT4829105736284` |
| `CIIT` e 14 dígitos | `CIIT48291057362841` |

As outras redes da GOFO seguem o mesmo padrão, mudando o país depois de `GF` ou `CI`. Códigos `GFFR` e `CIFR` pertencem à [GOFO France](carrier:gofo-fr), `GFNL` à GOFO Netherlands e `GFES` à GOFO Spain. `GFUS` com 14 dígitos, `GF` com apenas 13 ou `CR` com 12 são da [GOFO Express](carrier:gofo) nos Estados Unidos, uma rede separada, e `GFCA` com 14 dígitos pertence à [GOFO Canada](carrier:gofo-ca). Um código iniciado por `GFIT` com 12 ou 15 dígitos não é reconhecido pelo Peek como GOFO Italy: confira se há erro de digitação. Mais sobre como diferenciar códigos em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status do rastreamento GOFO: o que significam as frases em italiano

O Peek lê o rastreamento italiano da GOFO, então os registros aparecem nas palavras italianas da GOFO qualquer que seja o seu idioma. Os mais comuns, na ordem em que surgem:

| A GOFO mostra | O que significa para você |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | O remetente está preparando o pacote e logo o entregará à GOFO. |
| `Registrato presso il centro di trasferimento` | Registrado em um centro de transferência da GOFO. |
| `Il pacco ha lasciato il centro di smistamento` | Saiu de um centro de triagem da GOFO. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Chegou à unidade regional da GOFO que atende sua área. |
| `Preparazione alla consegna` | Sendo separado no depósito antes da saída do entregador. |
| `Il corriere è fuori per la consegna` | Com o entregador na rua. |
| `Consegna fallita, tipo:Destinatario assente` | Não entregue: não havia ninguém em casa. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Não entregue: o endereço está incorreto. |
| `Il pacco è stato restituito al centro di smistamento` | Voltou ao centro de triagem da GOFO após uma entrega que não deu certo. |
| `Consegnato` | Entregue. |

Duas mensagens confundem. Enquanto nenhum registro vier após a primeira, a GOFO ainda não tem o pacote, e o Peek exibe "Etiqueta criada". Já `Il pacco è stato restituito al centro di smistamento` não quer dizer que o pacote voltou para a loja: ele retornou ao centro de triagem da GOFO, e o Peek mostra "Precisa de atenção". A entrega aparece sempre apenas como `Consegnato`, em qualquer idioma: a linha completa da GOFO pode trazer detalhes sobre o destinatário, que o Peek omite.

O rastreamento da GOFO também classifica os pacotes em categorias amplas. `Avviso` sinaliza um problema registrado pela GOFO, exibido pelo Peek como "Precisa de atenção"; sozinho, não significa tentativa de entrega. `Restituito` indica devolução ao remetente, mostrado como "Devolvida ao remetente".

Se a [YunExpress](carrier:yunexpress) entregou seu pacote à GOFO Italy, o rastreamento da própria YunExpress pode repassar os eventos da GOFO em inglês: `Preparing for delivery` no depósito antes de o entregador sair, e `Package departed from sorting center` ao lado do italiano `Il pacco ha lasciato il centro di smistamento`.

## Pacote GOFO atrasado, entrega perdida, extravio ou avaria

**Sem movimento.** O Peek continua consultando a GOFO. Se passarem quatro dias sem nova leitura antes da saída para entrega, a página do pacote no Peek avisa e traz o link "Pedir ajuda a GOFO Italy" para a página de suporte italiana. Veja também [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entrega perdida.** Após `Consegna fallita, tipo:Destinatario assente` ou `Consegna fallita, tipo:L'indirizzo è errato`, o Peek exibe "Tentativa de entrega" — o mesmo vale para qualquer outro motivo após `tipo:`. O passo seguinte depende da GOFO: veja [entregas perdidas](guide:missed-delivery) para saber o que conferir.

**Atrasado.** Pelas regras de consumo da União Europeia, a loja precisa entregar em até 30 dias, salvo prazo combinado diferente. Se não cumprir, dê um prazo adicional razoável; se passar de novo, você pode cancelar o pedido e receber o dinheiro de volta.

**Marcado como `Consegnato`, mas não chegou.** A responsabilidade pela entrega é da loja: cabe a ela contatar a transportadora e comprovar a entrega e, se não conseguir, reenviar o item ou reembolsar você. Veja o que verificar ao redor de casa primeiro em [entregue, mas não recebido](guide:delivered-not-received).

**Avariado.** A loja responde por qualquer avaria do despacho até você receber o pacote, então confira diante do entregador sempre que puder. Se a caixa estiver visivelmente danificada, a AGCOM (reguladora postal italiana) orienta que você pode recusá-la ou aceitar com ressalva. Mais em [encomenda avariada](guide:damaged-parcel).

## Como reclamar sobre uma entrega da GOFO Italy

Comece pela loja que enviou, já que ela responde pela entrega. Para reclamar contra a própria transportadora, a AGCOM, autoridade reguladora de comunicações da Itália, define um fluxo único para operadoras postais — e encomendas de até 31,5 kg entram nessa categoria:

1. Envie reclamação por escrito à operadora, no formulário do site dela ou solicitado por e-mail. Ela tem 45 dias para responder.
2. Se a resposta não satisfizer ou não chegar em 45 dias, peça conciliação à operadora. O procedimento termina em até 60 dias.
3. Se isso também falhar, você tem 90 dias para pedir à AGCOM que resolva a disputa usando o Formulario CP. A AGCOM decide em até 150 dias, e sua decisão vincula ambas as partes.

A AGCOM pode determinar reembolsos ou indenizações previstas nas normas, mas não indenizações por danos adicionais; para isso — e em qualquer momento — você pode recorrer à Justiça. Se a operadora não responder à reclamação nem à conciliação, denuncie no Portale delle segnalazioni da AGCOM. O atendimento da AGCOM funciona de segunda a sexta-feira, das 10h às 14h, pelo telefone 800.185060 (fixo na Itália) ou 081.750750 (celular ou exterior).

## Atendimento ao cliente da GOFO Italy

A página de ajuda da GOFO Italy fica no site italiano em gofo.com, e o Peek exibe o link "Pedir ajuda a GOFO Italy" quando passam quatro dias sem novidade antes da saída para entrega. A empresa responsável, GOFO Italia s.r.l., tem sede registrada na Via Juan Manuel Fangio 11, 20045 Lainate (MI). Tenha em mãos o código `GFIT` ou `CIIT` e o número do pedido.

## Como rastrear um pacote da GOFO Italy no Peek

O [Peek](/) identifica códigos `GFIT` e `CIIT` sozinho. Você também pode colar links da página italiana da GOFO (em gofo.com ou gofoexpress.it, com `id=` e o número).

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, o Peek lê o rastreamento italiano da GOFO até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que a GOFO registra saída para entrega, enquanto o rastreador da GOFO responder (por serviços de rastreamento, até a cada 15 minutos naquele dia enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele mostra o status, o histórico com a localidade informada pela GOFO em cada leitura, o horário da entrega e esses locais no mapa, e pode avisar em "Cada movimentação", "Etapas importantes" ou "Só a entrega". Quando a GOFO informa apenas código de depósito ou o país, o evento aparece sem cidade. Antes da primeira leitura na GOFO, o Peek exibe "Aguardando GOFO Italy" e continua tentando, sem tratar o código como desconhecido de imediato. Se dois dias depois a GOFO ainda não reconhecer e nenhum outro serviço responder, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar uma vez por dia — e semanalmente após uma semana da adição. Se a GOFO listar o envio mas ainda sem leituras, ela já conhece o número: o Peek mantém "Aguardando GOFO Italy" sem pedir que você confira o código. O Peek exibe a data prevista da GOFO até a saída para entrega, entrega, devolução ou ponto de retirada; não mostra peso e omite fotos de entrega e avisos da GOFO. Ele não pode falar com a GOFO nem com a loja por você.

## Perguntas sobre o rastreamento GOFO

### Onde está meu pacote da GOFO?

A linha mais recente do rastreamento indica. `Arrivato alla struttura di destinazione regionale GOFO` significa que chegou à unidade regional da GOFO da sua área, `Preparazione alla consegna` que está sendo preparado para o entregador e `Il corriere è fuori per la consegna` que está na rua com o entregador. Se só aparecer a linha de preparação pelo remetente, ele ainda não entregou o pacote à GOFO.

### A GOFO é confiável?

A empresa italiana da GOFO é a GOFO Italia s.r.l., sediada em Lainate, na província de Milão. A AGCOM, reguladora postal italiana, registrou em sua Determina n. 12/26/DSP de 2 de setembro de 2026 que em 17 de junho de 2026 notificou a empresa por três supostas infrações: prestar atividade postal no âmbito do serviço universal sem a licença individual exigida, sem carta de serviços (Carta dei servizi) e sem procedimentos de resolução de disputas para usuários. Segundo a decisão, a empresa admitiu as acusações relativas à carta de serviços e aos procedimentos de disputa e pagou a multa correspondente em valor reduzido, levando a AGCOM a encerrar essa parte do processo. A empresa contestou a acusação sobre autorização inadequada, cuja apuração seguiu o trâmite ordinário.

### A GOFO é a mesma empresa que a CIRRO Parcel?

Na Itália, para fins de rastreamento, sim: a lista de transportadoras do Peek traz CIRRO Parcel Italy como outro nome para GOFO Italy. Um rastreador que diga apenas "Cirro" pode se referir às redes da GOFO na Itália, na França ou na Holanda.

### O que fazer se meu pacote da GOFO sumir?

Fale com a loja. Pelas regras da UE, ela responde pela entrega até o pacote chegar às suas mãos; se não puder provar a entrega, deve reenviar o produto ou reembolsar você. Se a falha for da transportadora, aplica-se o fluxo de reclamação da AGCOM explicado acima. Mais em [encomendas extraviadas](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., sede em Lainate, decisão da AGCOM de 2 de setembro de 2026 e pagamento da multa reduzida
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – reclamação em 45 dias, conciliação em 60, Formulario CP em 90, encomendas até 31,5 kg, encomendas avariadas e central de atendimento
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – entrega em até 30 dias, prazo extra e responsabilidade por avarias até o recebimento
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – loja comprova entrega e conferência na entrega
:::
