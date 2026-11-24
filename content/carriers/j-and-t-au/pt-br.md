---
title: Rastreamento J&T Express Austrália: códigos JTAU e status
description: Rastreie encomendas J&T Express na Austrália: formato JTAU de 16 caracteres, significado dos status, fotos de comprovação e telefone 13 18 18.
slug: rastreamento-jt-express-australia
published: 2026-11-24
updated: 2026-11-24
---

A J&T Express Australia (*J&T Express Australia Pty Ltd*, Villawood NSW) é o braço australiano da J&T Express, o grupo de entregas responsável pela J&T no Sudeste Asiático e na China. Ela entrega encomendas na Austrália para marketplaces, marcas e varejistas — incluindo pedidos internacionais vindos do exterior após o desembaraço aduaneiro. Se uma loja despachou seu pedido pela J&T na Austrália, o código de rastreamento começa com `JTAU`: cole-o na caixa acima ou consulte diretamente no [rastreador oficial da J&T Austrália](https://www.jtexpress.au/tracking). A loja que enviou o pedido informa esse número no e-mail de confirmação de envio ou na página do seu pedido.

## Código de rastreamento J&T Express Austrália: como identificar

A J&T chama o código de *tracking number* ou *waybill number*. Um conhecimento de transporte australiano da J&T é formado por `JTAU` seguido de 12 dígitos, totalizando 16 caracteres, e o Peek não conhece outro formato para essa rede (exemplos com dígitos inventados):

| Código (inventado) | O que é |
| --- | --- |
| `JTAU482910573649` | `JTAU` e 12 dígitos: encomenda J&T Express Australia. O Peek identifica J&T Express Australia imediatamente. |
| `JTAU48291057364` | Um dígito a menos: fora do padrão da J&T Express Australia, e o Peek não reconhece nenhuma transportadora; verifique se falta um algarismo. Se nenhuma transportadora responder, o `peektracker.com` alerta que o código começa como um número da J&T Express Australia mas está um caractere curto, sugerindo conferência com a mensagem de envio. |
| `JTUK482910573649` | `JTUK` e 12 dígitos: J&T Express UK, não a rede australiana. |
| `JTDE482910573649` | `JTDE` e 12 dígitos: [J&T Express Germany](carrier:j-and-t-de). |
| `JTE482910573649` | `JTE` e 12 dígitos: J&T Express Middle East, na Arábia Saudita, Emirados Árabes Unidos e Kuwait. |
| `JT4829105736491` | `JT` e 13 dígitos: redes da J&T no Sudeste Asiático e na China (J&T Express China). |

As redes da J&T em outros países mantêm seus próprios rastreadores e prefixos: apenas `JTAU` pertence à operação australiana. Espaços, hifens e letras minúsculas não fazem diferença no Peek (`jtau 4829 1057 3649` é lido exatamente como a primeira linha). O catálogo do Peek também reconhece a empresa pelos nomes J&T Express (AU), J&T Express AU, J&T Australia e JT Express Australia.

**Onde encontrar o código.** No e-mail de envio da loja, na página do pedido da loja ou marketplace, ou em qualquer mensagem de notificação sobre a entrega. A J&T recebe seu nome, endereço e dados de contato diretamente do remetente ou do marketplace onde você comprou; portanto, se não localizar o número, peça à loja ou consulte [como encontrar o código de rastreamento](guide:find-tracking-number).

**Pesquisa sem resultado.** O rastreador da J&T responde `No tracking records yet` para números que ainda não constam no sistema. Se o Peek continuar exibindo "Aguardando J&T Express Australia", confira todos os 16 caracteres com o e-mail da loja e depois consulte o vendedor.

## Significado dos status da J&T Express Austrália

O rastreador da J&T apresenta cada leitura com data, hora e uma frase em inglês, sem citar cidade ou depósito. Estas são as frases reais que o Peek encontra em encomendas da J&T Express Australia, na ordem habitual do trajeto:

| Status da J&T | O que significa para você | O Peek exibe |
| --- | --- | --- |
| `The parcel has been picked up from customs clearance.` | Uma encomenda internacional foi liberada pela alfândega e coletada pela J&T com o despachante aduaneiro — primeira leitura de pacotes importados. | "Recebida pela transportadora" |
| `The parcel has been successfully picked up and is being prepared for transportation.` | A J&T recebeu o pacote e o está preparando para transporte. | "Recebida pela transportadora" |
| `The parcel arrives at the distribution center and is prepared for transport to the next destination.` | Chegou a um centro de distribuição da J&T, pronto para o trecho seguinte. | "Em trânsito" |
| `The parcel is dispatched from the transit center to the next destination` | Saiu de um centro de trânsito da J&T rumo à próxima parada. | "Em trânsito" |
| `Your parcel is out for delivery.` | Está em rota com o entregador para entrega no endereço. | "Saiu para entrega" |
| `The building was closed when we arrived. We'll try again.` | O entregador encontrou o prédio ou estabelecimento fechado; a J&T fará nova tentativa. | "Tentativa de entrega" |
| `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.` | A entrega falhou porque o endereço parece incorreto ou incompleto, e a J&T solicita atualização do endereço. | "Tentativa de entrega" |
| `Your parcel has been delivered.` | Entregue ao destino. | "Entregue" |

Atenção: `The parcel has been picked up from customs clearance.` parece uma retenção aduaneira, mas indica o oposto — a liberação na alfândega terminou e a J&T já retirou o pacote. Saiba mais sobre importações em [encomendas retidas na alfândega](guide:customs).

Qualquer frase fora dessa tabela segue as regras gerais do Peek para todas as transportadoras; uma frase não reconhecida aparece como a J&T escreveu e conta como "Em trânsito", a menos que a encomenda já esteja em rota de entrega ou em etapa posterior, caso em que a etapa permanece inalterada.

No rastreador da J&T, algumas frases terminam com o número do atendimento ao cliente ou um convite para ligar. O Peek remove esses trechos, além de descartar textos entre colchetes, telefones e nomes de entregadores, motoristas e remetentes. Quem adicionou o pacote no Peek vê quem assinou ou recebeu a encomenda; já em links compartilhados aparece apenas "Entregue", sem divulgar nomes. Como as leituras não mencionam locais, nem o site da J&T nem o Peek conseguem indicar em qual estado ou depósito o pacote está.

## Entrega e comprovante fotográfico da J&T na Austrália

A J&T recruta parceiros locais em toda a Austrália para realizar a coleta e a entrega de última milha. Nas encomendas reais observadas pelo Peek, o entregador da J&T leva o pacote diretamente para entrega e conclui a entrega ou registra o motivo da falha, sem transferência para outra transportadora australiana: o rastreamento da J&T acompanha o envio até sua porta.

**Encomendas de linhas internacionais.** Operadoras internacionais que enviam compras do exterior podem repassar lotes à J&T na Austrália. A [Yanwen](carrier:yanwen), empresa chinesa de logística, entrega seus lotes à J&T no porto de chegada, e o rastreamento da própria Yanwen passa a reproduzir as frases da J&T (algumas acompanhadas do telefone de suporte da J&T). Se o seu pedido veio com um código Yanwen, continue seguindo aquele número no Peek: o Peek incorpora as leituras da J&T no histórico sem o telefone e mostra a coleta da J&T como "Em trânsito", já que a Yanwen tinha o pacote muito antes.

**Saída para entrega.** `Your parcel is out for delivery.` indica que a encomenda está em rota de entrega. Como o rastreador da J&T não informa janela horária nem data prevista, o Peek também não pode exibi-las.

**Prédio ou estabelecimento fechado.** `The building was closed when we arrived. We'll try again.` significa que a J&T levará o pacote em outro momento. Nos históricos reais observados, essa leitura surge logo após a saída para entrega e o pacote depois sai novamente do centro de trânsito. Se o prédio costuma ficar fechado durante o expediente, pergunte à loja se é possível usar outro endereço de entrega.

**Problema no endereço.** Após `We couldn't deliver - the address details appear to be incorrect. Please update your delivery address.`, revise o endereço do pedido. Se faltar algum detalhe, como número do apartamento ou código postal, avise a loja imediatamente, pois foi ela quem contratou o frete com os dados cadastrados. Veja [como alterar o endereço de entrega](guide:change-delivery-address) e [entrega não realizada](guide:missed-delivery).

**Tentativas, locais seguros e pontos de retirada.** O site da J&T Austrália não especifica quantas tentativas são feitas, se os motoristas deixam encomendas em locais seguros nem por quanto tempo guardam pacotes não entregues. Entre as frases conhecidas da J&T Express Australia no Peek, nenhuma envia o pacote para ponto de retirada ou armário inteligente. Em caso de dúvida, pergunte à loja ou verifique suas mensagens de entrega.

**Comprovante de entrega (Proof of delivery).** O aviso de privacidade da J&T descreve o registro feito na porta:

- O motorista registra cada entrega ou tentativa no aplicativo da J&T, podendo incluir fotos do pacote no local onde foi deixado, data e hora, localização do dispositivo do motorista, assinatura quando exigida e notas de entrega.
- A J&T instrui os motoristas a fotografar o pacote no local de entrega evitando enquadrar pessoas, placas de veículos ou o interior das residências sempre que possível.
- Você recebe o comprovante de entrega como link por SMS ou outra notificação eletrônica; qualquer pessoa com o link consegue abri-lo, e o comprovante não informa o nome do motorista.
- Se não quiser que tirem foto, a J&T orienta enviar instruções de entrega pelo contato de privacidade `dataprotection@jtexpress.au`.

## Encomenda J&T parada, perdida ou entregue mas não recebida

Como a loja escolheu a J&T, para qualquer compra online o vendedor é sempre o primeiro contato.

**Sem novas leituras.** Quando uma encomenda em "Recebida pela transportadora" ou "Em trânsito" passa quatro dias sem nova linha, o Peek exibe o alerta com a data da última leitura ("Sem atualizações desde seg., 12 de out.") e o link "Pedir ajuda a J&T Express Australia". Se nada mudar depois disso, fale com a loja ou com o atendimento da J&T (abaixo) informando seu código de rastreamento. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Ainda sem histórico.** Um número que o rastreador da J&T ainda não conhece aparece como "Aguardando J&T Express Australia". Lojas costumam criar o código antes de despachar o pacote. Se dois dias depois a J&T continuar sem reconhecer o código e nenhum outro serviço responder por ele, o Peek mostra "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente (e semanalmente depois de uma semana). Confira o número com a loja e pergunte se o envio já ocorreu.

**Consta entregue, mas não chegou.** Abra o link do comprovante de entrega enviado por SMS pela J&T e confira o local exibido na foto; depois procure ao redor da porta e pergunte a moradores e vizinhos. Ainda desaparecido? Avise a loja e a J&T com o código de rastreamento: o aviso de privacidade da J&T afirma que esses registros são usados para apurar pacotes extraviados, danificados ou entregues no lugar errado. Solicite reembolso ou reenvio à loja. Veja [entregue, mas não recebido](guide:delivered-not-received).

**Extravio ou avaria.** O site da J&T não estabelece um fluxo de indenização direta para destinatários. Como a loja ou marketplace também recebe o comprovante de entrega, solicite reembolso ou substituição ao vendedor informando seu código. Veja [encomenda perdida](guide:lost-parcel) e [encomenda danificada](guide:damaged-parcel).

## Atendimento ao cliente e contato da J&T Express Austrália

Conforme divulgado na [página de contato da J&T Austrália](https://www.jtexpress.au/contact):

- **Telefone:** `13 18 18`, horário de atendimento das `08:00` às `20:00` (a página não especifica os dias da semana).
- **E-mail:** `service@jtexpress.au`.

A mesma página traz endereços voltados a empresas (`business@jtexpress.au`) e interessados em ser parceiros de entrega (`partner@jtexpress.au`). O aviso de privacidade informa que os centros de atendimento e suporte ao cliente da J&T ficam na Malásia e nas Filipinas.

Em pedidos de lojas online, notifique sempre o vendedor; em marketplaces, use a ajuda da página do pedido. Quando a encomenda para de atualizar, o link "Pedir ajuda a J&T Express Australia" no Peek abre diretamente a página de contato da J&T.

Como a J&T envia SMS legítimos com o link do comprovante de entrega após entregar um pacote, uma mensagem citando a J&T não é automaticamente golpe. Porém, se um SMS exigir pagamento ou pedir dados pessoais ou bancários, confira [golpes por SMS de entrega](guide:delivery-scam-texts) antes de clicar em qualquer link.

## Rastrear encomendas J&T Express Austrália com o Peek

O [Peek](/) identifica a J&T Express Australia imediatamente a partir de um código `JTAU` ("Transportadora detectada"), com ou sem espaços e em maiúsculas ou minúsculas, consultando diretamente o rastreador da J&T apenas pelo código, sem exigir código postal nem conta. Links `www.jtexpress.au/tracking?q=` contendo o código também funcionam (se tiverem outro tipo de número, o Peek lê o código como se tivesse sido colado isoladamente). Para códigos que não comecem com `JTAU`, cole normalmente: o Peek acompanha mais de 4.500 transportadoras. Códigos `JT` com 13 dígitos correspondem às redes da J&T no Sudeste Asiático e na China: o Peek não consulta a J&T só pelo código, e sim serviços de rastreamento ("Consultando serviços de rastreamento…"). Escolher J&T Express Australia em "Trocar" para outro formato não funciona, pois o Peek avisa que ela "não emite códigos como este".

O Peek exibe a etapa, cada leitura no seu momento exato, mostrado no horário do seu aparelho (encerrando o histórico em `peektracker.com` e nos apps iPhone e Android com "Horários no seu fuso horário:" e o nome do fuso) e a hora da entrega. As leituras aparecem no inglês da J&T. Como a J&T não fornece local, data prevista nem peso, o Peek não exibe mapa nem a foto do comprovante (abra o link do SMS da J&T para ver a foto), e remove contatos de escritório, guardando até as 100 leituras mais recentes em históricos muito longos. A página do pacote também inclui um link direto para abrir seu código no site oficial da J&T Express Australia.

Enquanto a J&T não registra movimentações, o Peek continua consultando e exibe "Aguardando J&T Express Australia" (ou pede conferência após dois dias sem resposta). Se o rastreador da J&T estiver fora do ar ou não localizar o número, o Peek consulta serviços de rastreamento e mostra o histórico informado por eles.

Enquanto você mantém a encomenda aberta no Peek ou no dia da entrega com alertas ativados, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h no horário local no dia em que a J&T registra saída para entrega, enquanto o rastreador próprio da J&T responde (pelos serviços de rastreamento, consulta de hora em hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados); fora dessas condições, verifica de hora em hora. Após dois dias sem novidades (12 horas em "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a J&T nem a loja, alterar o endereço nem agendar uma nova tentativa de entrega.

## Perguntas frequentes sobre a J&T Express Austrália

### A J&T Express Australia é confiável?

Sim. O registro comercial australiano (Australian Business Register) lista a *J&T EXPRESS AUSTRALIA PTY LTD* como empresa privada australiana com ABN ativo (`18 698 743 609`) desde 4 de junho de 2026 e sede comercial principal em Nova Gales do Sul; o aviso de privacidade da empresa indica um escritório registrado em Villawood NSW. Ela faz parte do grupo J&T Express, listado em Hong Kong (`01519.HK`) e fundado na Indonésia em 2015. Seus códigos começam com `JTAU`, e todas as frases da tabela acima foram lidas em pacotes reais da J&T na Austrália.

### O que significa `picked up from customs clearance` no rastreamento J&T?

Significa que a alfândega já concluiu a liberação. A encomenda veio do exterior, passou pelo desembaraço aduaneiro e a J&T a coletou com o despachante para iniciar a entrega. É a primeira leitura de um pacote importado na J&T, e o Peek a exibe como "Recebida pela transportadora", não como retenção alfandegária.

### Por que meu código J&T não foi encontrado?

Verifique se ele começa com `JTAU` seguido de 12 dígitos: códigos de outras operações da J&T, como `JTUK` no Reino Unido, não pertencem à Austrália. Se o número estiver correto, a J&T pode ainda não ter coletado o pacote (`No tracking records yet` no site, "Aguardando J&T Express Australia" no Peek). Se após dois dias ainda não houver resposta de nenhum serviço, o Peek pede para conferir o código, trocar a transportadora ou excluir a encomenda; persistindo após vários dias, fale com a loja.

### Como entrar em contato com a J&T Express Austrália?

Ligue para `13 18 18` (atendimento das `08:00` às `20:00`) ou envie e-mail para `service@jtexpress.au`, conforme a página de contato. Em compras online, informe também a loja que contratou a entrega.

### A J&T Express Austrália tira foto na entrega?

Sim. O aviso de privacidade da J&T informa que os motoristas fotografam o pacote e o local onde foi deixado, e o destinatário recebe o link do comprovante por SMS ou outra notificação eletrônica.

:::sources
- [J&T Express Australia: Track & Trace](https://www.jtexpress.au/tracking) – rastreador oficial, tracking/waybill number e `No tracking records yet`
- [J&T Express Australia: Contact Us](https://www.jtexpress.au/contact) – telefone `13 18 18`, horário de atendimento e e-mails de suporte, comercial e parceiros
- [J&T Express Australia: Delivery Partners](https://www.jtexpress.au/career) – parceiros de entrega e coleta de última milha na Austrália
- [J&T Express Australia: About Us](https://www.jtexpress.au/about) – grupo J&T Express (`01519.HK`), fundado na Indonésia em 2015
- [J&T Express Australia: Privacy Notice](https://www.jtexpress.au/privacy-policy) – razão social em Villawood NSW, comprovante fotográfico por SMS, `dataprotection@jtexpress.au` e atendimento na Malásia e Filipinas
- [ABN Lookup: J&T Express Australia Pty Ltd](https://abr.business.gov.au/ABN/View?abn=18698743609) – ABN `18 698 743 609` ativo desde 4 de junho de 2026 em Nova Gales do Sul
- [Universal Parcel Scraper: J&T Express Australia](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/j-and-t-au) – nomes, formato `JTAU`, links, frases de status, integração Yanwen, últimas 100 leituras e página de ajuda
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formatos das outras redes da J&T, sem repasse a outra transportadora, serviços de rastreamento como alternativa, repasse em lote da Yanwen à J&T
:::
