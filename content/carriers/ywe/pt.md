---
title: Seguimento YWE (YanwenExpress): número, estados e ajuda
description: Segue uma encomenda YWE (YanwenExpress) nos Estados Unidos: formato do número YWE, significado de cada leitura do centro até à porta e quem contactar.
slug: seguimento-ywe
published: 2026-11-18
updated: 2026-11-20
---

A YWE (YanwenExpress, operada pela Yanwen Express LLC) é uma transportadora de entrega de última milha nos Estados Unidos: leva encomendas desde os seus centros operacionais norte-americanos até à porta do destinatário para lojas online, retalhistas e marketplaces. Os seus números são compostos pelas letras YWE seguidas de 14 dígitos: cola o teu na caixa acima ou consulta-o diretamente no localizador da YWE em [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Número de seguimento YWE: como é

Os números da YWE têm 17 caracteres no total: o prefixo YWE e 14 algarismos. Exemplos com dígitos inventados:

| Número | O que é |
| --- | --- |
| `YWE48291057364918` | YWE e 14 dígitos: um número de seguimento da YWE. |
| `YWE4829105736491` | Apenas 13 dígitos: não corresponde ao formato da YWE. Confirma se falta um algarismo; se nenhuma transportadora responder por ele, a respetiva página em peektracker.com mostra «Começa como um número de seguimento de YWE, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» |
| `YWLAX482910573649` | YW, três letras e 12 dígitos: um número da [Yanwen](carrier:yanwen), não da YWE. |

Os espaços e as letras minúsculas não interferem quando colas o código no Peek.

**Onde encontrá-lo.** No e-mail de expedição enviado pela loja ou na página da tua encomenda. Se não o encontrares, vê [como encontrar o número de seguimento](guide:find-tracking-number).

**Uma ligação de seguimento.** Uma ligação para o rastreador da YWE terminada em `?label=` e um número YWE de 14 dígitos abre diretamente essa encomenda na YWE quando colas o endereço completo no Peek. O portal da YWE aceita até 30 números de cada vez (se introduzires mais, guarda os primeiros 30).

## Estados de seguimento da YWE explicados

O portal de seguimento da YWE apresenta cada leitura com data e hora, diferença face a GMT e cidade e estado dos EUA, da mais recente para a mais antiga. As mensagens surgem em inglês. Pela ordem habitual do percurso nos Estados Unidos:

| A YWE apresenta | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.` | O remetente registou o envio junto da YWE, mas a YWE ainda não recebeu fisicamente a encomenda. | «Etiqueta criada» |
| `Container picked up by YWE,LAX01 Awaiting Item.` | A YWE recolheu nos Estados Unidos o contentor aéreo onde a encomenda viajou; o pacote individual ainda não foi lido. | «Em trânsito» |
| `Container received` | O centro operacional da YWE nos EUA deu entrada ao contentor. | «Em trânsito» |
| `Parcel received by in LAX01.` | Primeira leitura individual da encomenda no centro norte-americano da YWE. `Parcel received by NJC01.`, sem «in», representa a mesma receção noutro centro. | «Em trânsito» |
| `Parcel departed from Facility.` | Saiu do centro de triagem. | «Em trânsito» |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit - departure`, `In transit to next facility.` | Passagem por um centro de trânsito intermédio nos Estados Unidos, antes da estação responsável pela tua zona. | «Em trânsito» |
| `Parcel received by local  Facility.` | Chegada à estação local de distribuição nos Estados Unidos que entrega na área do destinatário. | «Em trânsito» |
| `Parcel is out for delivery.` | Saiu para distribuição nos Estados Unidos. | «Em distribuição» |
| `Parcel has been delivered.(Front door)` | Entregue nos Estados Unidos. O texto entre parênteses indica o local onde foi deixada. | «Entregue» |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | A tentativa de entrega nos Estados Unidos falhou pelo motivo entre parênteses retos; a encomenda regressa ao armazém norte-americano da YWE, não ao remetente. | «Tentativa de entrega» |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | O mesmo regresso ao armazém da YWE nos Estados Unidos formulado como falha na entrega, de onde pode voltar a sair para distribuição. | «Tentativa de entrega» |
| `Return to sender` | A devolução ao remetente foi iniciada, mas a encomenda ainda não chegou às mãos dele. | «Problema reportado» |
| `Returned to sender, shipment has been received in sender warehouse.` | Devolvida e recebida no armazém do remetente. | «Devolvida ao remetente» |

**Códigos de centro, motivos e locais de entrega.** `LAX01` e `NJC01` são códigos de centros da YWE nos Estados Unidos: leituras noutras instalações trazem o respetivo código nesse lugar. Da mesma forma, `[Restricted Address]` ou `[Wrong Address]` são exemplos de motivos de falha e `(Front door)` um exemplo do sítio onde o volume ficou.

**O separador Alert nem sempre significa problema.** O rastreador da YWE organiza os envios nos separadores `Awaiting`, `Transit`, `Delivered`, `Alert` e `Returned` consoante a última leitura. Coloca `Parcel received by local  Facility.` (com espaço duplo antes de `Facility.`), a receção na estação de entrega local, em `Alert`, embora seja uma etapa normal. O Peek apresenta-o como «Em trânsito».

**Outras formulações.** Qualquer leitura que não conste desta tabela aparece no Peek tal como a YWE a escreveu, e o Peek identifica a etapa pelas palavras sempre que consegue.

**Horas.** Cada registo traz a diferença face a GMT do centro da YWE onde ocorreu. O Peek lê esse instante exato e converte todas as horas para o fuso horário do teu dispositivo; em peektracker.com e na aplicação para iPhone, o histórico termina com «Horas no teu fuso horário:» seguido do nome do fuso.

## Quem entrega as encomendas da YWE

**Estados Unidos.** Nos Estados Unidos, a YWE assegura a última milha através daquilo a que o seu site chama «Delivery Service Partner networks nationwide»: equipas locais de distribuição parceiras em todo o território norte-americano que se juntam à rede para entregar ou recolher encomendas. No código do Peek, o histórico de uma encomenda YWE vai do centro norte-americano da YWE até à porta do destinatário sem indicar qualquer outra transportadora.

Na página institucional, a YWE refere ter começado em Los Angeles em 2024 e planear cobrir a maior parte dos Estados Unidos até ao final de 2026.

**Tentativas de entrega e levantamento nos Estados Unidos.** A YWE não publica regras sobre o número de tentativas nos Estados Unidos, avisos deixados na caixa de correio ou pontos de recolha. Após uma tentativa falhada nos Estados Unidos, a leitura informa que o volume volta ao armazém da YWE (`Parcel will be returned to the warehouse.`). Para pedir nova tentativa ou deixar indicações de acesso, envia um e-mail à YWE (ver abaixo) com o número de seguimento, a morada completa nos Estados Unidos e as instruções de entrega. Vê também [entrega falhada](guide:missed-delivery).

**Portugal, Brasil e outros países fora dos Estados Unidos.** A YWE não anuncia qualquer serviço fora dos Estados Unidos. Se um vendedor te indicou um número YWE para uma morada em Portugal, no Brasil ou noutro país fora dos EUA, pergunta à loja qual a transportadora que faz efetivamente a entrega no destino.

**YWE e Yanwen.** A [Yanwen](carrier:yanwen) surge no Peek como uma transportadora separada, com formatos próprios de número e portal próprio, e o site da YWE não esclarece se as duas empresas estão ligadas. Quando o seguimento da Yanwen identifica a `YanwenExpress` como distribuidor de uma encomenda destinada aos Estados Unidos e fornece um código YWE, o Peek apresenta «Entrega: YWE» e consulta também o portal da YWE. Enquanto a YWE tiver apenas o pré-aviso sem leitura própria, surge «Anunciada a YWE, ainda não recebida». Assim que a YWE regista uma leitura tão ou mais recente do que a última da Yanwen, o Peek passa a acompanhar o envio na YWE mantendo os registos anteriores da Yanwen.

## Encomenda YWE atrasada ou seguimento parado

**`NOT_FOUND`.** O portal da YWE mostra este aviso quando não encontra leituras para o código pesquisado e aponta causas possíveis: a encomenda ainda não foi aceite ou lida, o número está incorreto ou o envio já tem tanto tempo que o histórico deixou de estar disponível. O Peek mostra «À espera de YWE» e continua a tentar. Confere o código na mensagem do vendedor: YWE e 14 dígitos. Se nada surgir ao fim de alguns dias, pergunta à loja se a encomenda chegou a seguir viagem.

**Parada em `Shipping Order Created.`** A YWE recebeu os dados eletrónicos do pedido, mas ainda não tem o volume consigo. Como a primeira leitura física da YWE acontece no seu centro nos Estados Unidos, pergunta ao vendedor onde se encontra a encomenda e quando foi expedida. Se saiu da China rumo aos Estados Unidos, consulta [seguir uma encomenda vinda da China](guide:tracking-from-china).

**Parada em `Container picked up by YWE,LAX01 Awaiting Item.`** A YWE já recolheu o contentor aéreo nos Estados Unidos, mas ainda não leu individualmente a tua encomenda à saída do contentor.

**`Parcel delivery abnormal` ou `Parcel delivery failed`.** A entrega nos Estados Unidos falhou pelo motivo indicado entre parênteses retos e a encomenda regressa ao armazém da YWE. Envia e-mail à YWE com o código de seguimento, a morada completa nos Estados Unidos e eventuais indicações de entrega, e avisa igualmente o vendedor.

**`Return to sender`.** A encomenda segue de volta para o remetente. Assim que surgir `Returned to sender, shipment has been received in sender warehouse.`, o armazém do remetente já a recebeu: pede à loja o reembolso ou novo envio. Vê [devolvida ao remetente](guide:returned-to-sender).

**Dada como entregue nos Estados Unidos, mas não chegou.** Os parênteses da leitura de entrega, como `(Front door)`, dizem onde foi deixada. Nas encomendas entregues nos Estados Unidos, o portal da YWE disponibiliza a ligação `view receipt`: introduz o código ZIP norte-americano no campo `Recipient's postal code` para veres as fotografias de entrega quando existirem. Em seguida escreve à YWE e informa o vendedor. Consulta [entregue mas não recebida](guide:delivered-not-received).

**Encomenda perdida e reclamações.** Segundo a política de privacidade do portal de clientes da YWE, o registo de conta está limitado a clientes empresariais (`corporate/business clients`), pelo que o cliente da YWE é a loja ou o marketplace que contratou a distribuição nos Estados Unidos, e não o destinatário. O site da YWE não apresenta formulário de indemnização para particulares. Pede o reembolso ou a substituição diretamente ao vendedor ou à plataforma de compra. Vê [encomenda perdida](guide:lost-parcel) e [porque é que o seguimento não atualiza](guide:tracking-not-updating).

## Contactos e apoio ao cliente da YWE

O site da YWE, disponível apenas em inglês, indica:

- **E-mail**: consta um endereço no rodapé de todas as páginas de `yanwenexpress.com`. Ao clicar abre-se uma janela com os dados que pedem para incluir: a tua dúvida, o número de seguimento da encomenda e, no corpo da mensagem, a morada completa do destinatário, instruções de entrega, contactos do destinatário (telefone e e-mail) e o pedido concreto.
- **Telefone**: nenhum divulgado. A página `Customer support` escreve «Calling us for further enquiries», mas não mostra número de telefone nem horário de atendimento.
- **Perguntas frequentes (FAQs)**: encontram-se na página `Customer support`, dirigidas a parceiros de distribuição (`Delivery Service Partner`) e não a quem aguarda uma encomenda.

Sobre a tua compra ou reembolso, fala com o vendedor; sobre a entrega nos Estados Unidos em si, escreve para o e-mail no rodapé do site da YWE.

## Seguir uma encomenda YWE com o Peek

O [Peek](/) reconhece de imediato um número YWE pelo formato — YWE e 14 dígitos —, exclusivo no seu catálogo, e interroga diretamente o rastreador da YWE. Também funciona colar uma ligação `yanwenexpress.com/tracking.html?label=` com um número YWE. Não precisas de conta, código postal nem número de telefone.

O Peek mostra o estado atual, cada leitura com localidade, estado e país e a hora exata da entrega. As leituras repetidas são eliminadas, tal como o nome de quem recebeu o volume quando uma leitura de entrega o inclui.

No seu próprio portal, a YWE pode ainda apresentar uma janela de entrega de duas horas durante o transporte nos Estados Unidos, um mapa com a posição do motorista e as fotografias de entrega acessíveis por código ZIP. O Peek deixa esses elementos de fora, bem como as linhas de rua de cada leitura, e não apresenta data prevista de entrega.

Como o portal da YWE não menciona outra transportadora, numa encomenda adicionada diretamente pelo número YWE o Peek não exibe linha «Entrega:» e mantém a consulta na YWE até à entrega. Se o servidor da YWE estiver temporariamente indisponível, o Peek recorre entretanto a serviços de seguimento.

Enquanto tiveres a encomenda aberta, ou no dia da entrega com as notificações ativas, o Peek verifica novidades até a cada 10 minutos — e até a cada 2 minutos a partir das 6:00 locais no dia em que sai para distribuição — enquanto o portal próprio da YWE responder (através apenas dos serviços de seguimento: de hora a hora, a cada 3 horas após um dia sem novas leituras e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ligadas); fora disso, e ao fim de dois dias sem novidades (12 horas enquanto continuar em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes escolher alertas para «Todas as leituras», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a YWE nem o vendedor por ti.

## Perguntas frequentes sobre o seguimento YWE

### O que é a YWE (YanwenExpress)?

É uma empresa de distribuição de última milha nos Estados Unidos gerida pela Yanwen Express LLC. Entrega encomendas de lojas online, retalhistas e marketplaces através de uma rede de equipas locais parceiras nos EUA e afirma ter iniciado atividade em Los Angeles em 2024.

### A YWE é a mesma empresa que a Yanwen?

O Peek trata-as como duas transportadoras distintas: a Yanwen tem números e portal próprios, e o site da YWE não diz se existe ligação entre ambas. Quando o seguimento da Yanwen aponta a `YanwenExpress` como distribuidora nos Estados Unidos com um código YWE, o Peek acompanha também esse código no portal da YWE.

### Onde posso seguir uma encomenda da YWE?

Na caixa de pesquisa no topo desta página ou no portal da YWE em `yanwenexpress.com`, que aceita até 30 números em simultâneo. Usa o código completo: YWE seguido dos 14 dígitos.

### O que significa «Container picked up by YWE»?

Significa que a YWE recolheu nos Estados Unidos o contentor aéreo onde a tua encomenda chegou. O código depois da vírgula, como `LAX01`, identifica o centro norte-americano da YWE. A primeira leitura individual do teu pacote (`Parcel received by in LAX01.`) surge mais tarde, na abertura do contentor. O Peek mostra «Em trânsito» em ambas.

### Quem devo contactar se faltar uma encomenda da YWE?

Primeiro o vendedor ou marketplace onde fizeste a compra, porque apenas clientes empresariais contratam envios com a YWE. Para questões sobre a entrega nos Estados Unidos, escreve à YWE através do e-mail no rodapé do site indicando o número de seguimento; a YWE não divulga telefone.

:::sources
- [YanwenExpress: página inicial](https://www.yanwenexpress.com/) – entregas de última milha nos Estados Unidos através de redes de Delivery Service Partners, até 30 números de uma vez, e-mail no rodapé e dados pedidos
- [YanwenExpress: About us](https://www.yanwenexpress.com/about.html) – arranque em Los Angeles em 2024 e plano para cobrir a maior parte dos Estados Unidos até ao final de 2026
- [YanwenExpress: Tracking](https://www.yanwenexpress.com/tracking.html) – leituras com diferença GMT, cidade e estado, separadores Awaiting, Transit, Delivered, Alert e Returned, aviso NOT_FOUND e causas possíveis, janela de entrega, mapa do motorista, «view receipt» com código postal do destinatário e limite de 30 códigos
- [YanwenExpress: Customer support](https://www.yanwenexpress.com/contactus.html) – referência a contacto telefónico sem número nem horário visíveis e FAQs para Delivery Service Partners
- [YanwenExpress: Join us](https://www.yanwenexpress.com/joinus.html) – equipas de entrega nos Estados Unidos convidadas a integrar a rede de última milha e recolha
- [YanwenExpress: Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC e registo de contas reservado a clientes empresariais
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato YWE de 14 dígitos, portal e ligação `?label=`, textos das leituras e estados normalizados, códigos de centro, classificação em Alert, diferenças GMT, campos mantidos ou descartados pelo Peek e transição a partir da Yanwen
:::
