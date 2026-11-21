---
title: Seguimento SPX Express Brasil: encomendas Shopee e estados
description: Segue uma encomenda SPX Express de uma compra na Shopee no Brasil: onde encontrar o código BR, significado dos estados, Agência Shopee, prazos e reembolso.
slug: seguimento-spx-express-brasil
published: 2026-11-21
updated: 2026-11-21
---

A SPX Express (Shopee Xpress) é a rede logística própria da Shopee no **Brasil**. Recolhe as encomendas junto dos vendedores e entrega-as ao domicílio ou numa *Agência Shopee*, um estabelecimento parceiro onde o destinatário levanta o volume. A Shopee designa este serviço por *Entrega Padrão* e indica que todo o acompanhamento — estado da encomenda, data prevista e eventual reembolso — é feito na aplicação da Shopee no Brasil. Também podes consultar o envio em [spx.com.br](https://spx.com.br/track) ou colar o código na caixa acima.

## Número de seguimento da SPX Express: onde encontrá-lo e formato

Na aplicação da Shopee no Brasil, acede a `Eu` > `Minhas Compras`, escolhe a encomenda em `A Caminho` (alguns artigos de ajuda da Shopee referem `A Receber`) e toca em `Status de Envio` para ver cada registo. A Shopee disponibiliza aí o código automaticamente assim que é criado, juntamente com a data prevista de entrega.

Estes são os formatos que o Peek reconhece (com caracteres inventados):

| Formato | O que identifica |
| --- | --- |
| `BR4829105736491` | SPX Express no Brasil: `BR`, 12 algarismos e um algarismo final (15 caracteres). |
| `BR482910573649X` | SPX Express no Brasil: `BR`, 12 algarismos e uma letra final (15 caracteres). |
| `BR482910573649XSPXLM48291057` | SPX Express: esse código de 15 caracteres seguido de `SPXLM` e 8 algarismos, tal como surge em algumas etiquetas de marketplace. |
| `EA482910576BR` | Duas letras, nove algarismos e `BR`: um código postal dos Correios do Brasil, não da SPX. |

O identificador da compra (`ID do pedido`) não é um número de seguimento, mas o apoio ao cliente da Shopee pode pedi-lo.

Nem todas as compras na Shopee no Brasil seguem pela SPX. A Shopee distingue *Entrega Padrão* (Shopee Xpress), *Entrega Correios* (cujo código também pode ser pesquisado nos Correios do Brasil) e *Entrega do Vendedor*, em que o próprio vendedor assegura a entrega e atualiza o estado. Nas encomendas enviadas do estrangeiro para o Brasil, a Shopee refere os Correios — com código local na aplicação — ou a parceira Anjun Courier; ainda assim, o seguimento da SPX apresenta linhas próprias para algumas remessas internacionais (ver abaixo). Para outros códigos, consulta [formatos de números de seguimento](guide:tracking-number-formats) e [como encontrar o número de seguimento](guide:find-tracking-number).

## O que significam os estados de seguimento da SPX Express

A SPX apresenta as leituras no português usado no Brasil (com algumas designações em inglês); várias linhas indicam a localidade a seguir a dois pontos.

| Linha na SPX | O que significa para a encomenda | O Peek mostra |
| --- | --- | --- |
| `Pedido em preparação` | O vendedor está a preparar a encomenda; a SPX ainda não tem o volume. | «Etiqueta criada» |
| `Pedido postado …` | O vendedor entregou o volume num ponto de depósito no Brasil (spx.com.br acrescenta a cidade); a SPX vai recolhê-lo aí. | «Aceite pela transportadora» |
| `Seu pedido foi coletado` | A SPX recolheu a encomenda junto do vendedor. | «Aceite pela transportadora» |
| `Seu pedido chegou ao centro logístico: …` | Chegou a um centro logístico da SPX no Brasil. | «Em trânsito» |
| `Seu pedido saiu do centro logístico: …` | Saiu de um centro logístico para a etapa seguinte. | «Em trânsito» |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | Em triagem; a SPX prevê novas leituras no prazo de um dia. | «Em trânsito» |
| `Pedido em trânsito` | Em ligação entre centros. Entre estados brasileiros ou cidades distantes, explica a Shopee, o volume passa dias no camião de transferência e a leitura seguinte só surge quando é descarregado e lido no centro de distribuição do estado de destino. | «Em trânsito» |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | Saiu para distribuição rumo à morada de destino nas próximas horas. | «Em distribuição» |
| `Pedido deve ser retirado na agência: …` | O volume aguarda levantamento pelo destinatário na agência indicada. | «Pronta para levantamento» |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …`, `Insucesso na entrega por endereço incorreto…` | A entrega não pôde ser concluída nesse dia; a SPX reorganiza a volta para nova tentativa na morada. | «Tentativa de entrega» |
| `Pedido entregue` | Entregue. | «Entregue» |
| `Pedido será devolvido ao vendedor`, `Pedido será devolvido ao vendedor. Por favor, aguarde.` | Segue de volta para o vendedor; a segunda variante apenas pede que aguardes. | «Devolvida ao remetente» |
| `Pedido devolvido` | Devolvida ao vendedor. | «Devolvida ao remetente» |
| `Pedido extraviado. Estorno será processado automaticamente.` | Encomenda dada como extraviada; a SPX indica que o reembolso será processado automaticamente. | «Problema reportado» |
| `Pedido descartado` | Após falha na entrega, o volume foi descartado: não foi entregue nem devolvido ao vendedor. Contacta a Shopee. | «Problema reportado» |

Alguns envios recebem antes leituras da frota de entrega da SPX iniciadas por `Seu pacote`, como `Seu pacote foi recebido pelo centro de triagem`, `Seu pacote foi recebido pelo centro de distribuição` (o centro que serve a zona do destinatário), `Seu pacote está sendo entregue pelo entregador` (com o hub no início: em distribuição) e `Seu pacote foi entregue`. O Peek atribui-lhes as mesmas etapas, como «Em distribuição».

**Encomendas vindas do estrangeiro para o Brasil.** As leituras que começam por `[China]` dizem respeito à origem (por exemplo, `[China]O pedido foi liberado na alfândega de exportação` após a saída aduaneira). Seguem-se `Pedido chegou no Brasil`, `Pedido está na alfândega de importação`, `Pedido em inspeção na alfândega de importação` e, em inglês, `Import Customs Cleared` (desalfandegamento concluído). O Peek mostra «Na alfândega» nas duas etapas aduaneiras e regressa a «Em trânsito» após `Import Customs Cleared`.

Já a linha `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega` significa que a alfândega brasileira recusou a importação e que a remessa será devolvida ou apreendida; o Peek mostra «Problema reportado». Nesse caso, pede o reembolso na Shopee pelos passos habituais.

## Entrega da SPX no Brasil: tentativas, palavra-chave e Agência Shopee

As regras abaixo aplicam-se exclusivamente a entregas da Shopee no **Brasil**:

**Prazos.** Nem o comprador nem o vendedor podem marcar dia ou horário de entrega. A Shopee aponta de 1 a 25 dias úteis a contar da recolha para encomendas de vendedores situados no Brasil, e habitualmente de 30 a 50 dias seguidos para compras internacionais. A data estimada consta da página da encomenda.

**Tentativas.** Os parceiros logísticos da Shopee efetuam 3 tentativas de entrega antes da devolução ao vendedor, sendo o reembolso automático quando a entrega não se concretiza. A morada só pode ser alterada antes da confirmação do pagamento e uma única vez; depois disso, a Shopee aconselha aguardar o fim das tentativas para receber o reembolso. Vê também [entrega não conseguida](guide:missed-delivery).

**Palavra-chave (*palavra-chave*).** Certas encomendas no Brasil têm uma palavra-passe aleatória para indicar ao estafeta à porta; só quem a conhece pode receber o volume. Consta da página da encomenda, do e-mail e da notificação da Shopee. Se não estiveres em casa, podes confiá-la a alguém de confiança, como um vizinho ou o porteiro do prédio (*porteiro*). Nunca a reveles antes de teres a encomenda na mão e recusa volumes em nome de outra pessoa ou com outra morada.

**Levantamento em Agência Shopee (`Retire Fácil`).** Onde disponível no Brasil, seleciona esta opção no fecho da compra em `Opção de Envio` > `Ver mais` > `Retire Fácil`; depois não é possível mudar para entrega em casa. Quando o volume chega à agência, a Shopee envia um código PIN por WhatsApp, e-mail ou na aplicação, onde surge também o último dia para levantamento. Apresenta o PIN e o número de seguimento ao balcão; qualquer pessoa com ambos pode levantar a encomenda por ti. O prazo de guarda é de 7 dias úteis antes de a SPX recolher o volume. Se uma agência ficar temporariamente indisponível, a Shopee pode encaminhar o envio para a morada predefinida. O [mapa de agências da SPX](https://spx.com.br/service-point) mostra os pontos ao escolher `Retirar um pacote`.

## Compras internacionais para o Brasil: Remessa Conforme e impostos

No Brasil, a Receita Federal inclui a Shopee na lista de empresas certificadas no Programa *Remessa Conforme* (PRC). Num site certificado, o imposto de importação e o ICMS são cobrados no ato da compra. A Receita Federal recomenda confirmar que os impostos surgem discriminados no ecrã de pagamento: caso contrário, serão exigidos à chegada da mercadoria ao Brasil sem a redução da taxa de importação.

Taxas indicadas na página da Receita Federal no Brasil para declarações registadas a partir de 12 de maio de 2026:

- **Imposto de importação** em plataforma certificada no PRC, para remessas destinadas a pessoa singular: 0 % até 50 US$ de valor aduaneiro (soma dos bens, transporte e seguro); acima de 50 US$, 60 % com dedução equivalente a 30 US$ sobre o imposto a pagar.
- **ICMS** (imposto estadual brasileiro): atualmente entre 17 % e 20 % consoante o estado brasileiro, em sites certificados ou não.

A Shopee refere que um CPF (número fiscal brasileiro) irregular pode impedir compras internacionais e que os impostos de importação não são reembolsáveis. Se a alfândega brasileira retiver um envio entregue pela Anjun Courier, a Shopee pede comprovativo de pagamento e do valor da compra, cópia de documento de identificação brasileiro (RG ou CNH) e o formulário de comprovação de valor da Receita Federal (RCV). Nos envios entregues pelos Correios do Brasil, verifica no respetivo seguimento se existe alguma formalidade pendente. A Receita Federal sublinha que não envia *boletos*, não telefona nem envia e-mails para cobrar estes valores. Mais detalhes em [encomendas retidas na alfândega](guide:customs).

## Encomenda SPX sem atualização, atrasada ou não recebida

**Dias seguidos sem leituras.** A Shopee explica que é comum no Brasil: o seguimento só muda quando o volume entra num novo centro e é lido, pelo que viagens longas de camião entre estados, fins de semana e filas nos hubs deixam o estado parado. Um trajeto que parece afastar-se do destino também faz parte do planeamento da rede, e muitas vezes surgem várias etapas de uma só vez quando a encomenda sai para distribuição. Consulta também [seguimento que não atualiza](guide:tracking-not-updating).

**Código não funciona.** O volume pode ainda não ter sido recolhido, ou o código pode ter um erro ou não estar ainda registado no sistema. Fala com o vendedor no chat da Shopee; segundo a plataforma, isso não significa que a encomenda esteja perdida.

**Atrasada.** Aguarda que passe a data prevista de entrega. A partir daí podes pedir reembolso na aplicação: `Eu` > `A Caminho` > a encomenda > `Pedido de Reembolso`, com o motivo `Não recebi o pedido`. O vendedor dispõe de 2 dias para responder, findos os quais o reembolso avança automaticamente. A Shopee indica que a maioria das encomendas atrasadas chega até 5 dias após a data prevista.

**Dada como entregue, mas não recebida.** Confirma com vizinhos, familiares e na portaria (*porteiro*) do edifício, e pede depois o reembolso na aplicação: na *Entrega Padrão*, no prazo de 7 dias após a entrega. A Shopee pode pedir uma averiguação à transportadora (*acareação*), que pode contactar-te por WhatsApp ou e-mail ou enviar novamente o estafeta à morada. Vê também [entregue mas não recebida](guide:delivered-not-received).

**Devolvida.** Após `Pedido será devolvido ao vendedor`, o volume regressa ao vendedor. Quando a entrega falha, a Shopee processa o reembolso automaticamente; vê [devolvida ao remetente](guide:returned-to-sender).

## Contactos da SPX Express: atendimento através da Shopee

A Shopee esclarece que o comprador não pode contactar diretamente a transportadora sobre seguimento ou entrega: o apoio é assegurado pela própria Shopee ao abrigo da *Garantia Shopee* no Brasil:

- **Chat Shopee:** na aplicação, abre `Eu` > `Central de Ajuda`, desce até `Atendimento ao Cliente` e escolhe `Chat Shopee`. Disponível de segunda a sexta-feira das 8h00 às 22h00 e aos sábados e domingos das 8h00 às 20h00 (horário de Brasília).
- **Formulário de pedidos de apoio:** disponível a qualquer hora na [página de horários do Atendimento ao Cliente da Shopee](https://help.shopee.com.br/portal/4/article/141507), indicando o `ID do pedido`. (O rodapé de `spx.com.br`, destinado a motoristas parceiros e agências, indica também o WhatsApp `(11) 2770-1438`, de segunda a sábado exceto feriados, das 8h00 às 20h00, horário de Brasília.)
- **Conduta imprópria do estafeta** (por exemplo, exigência de valores extra): participa através do `Chat Shopee`.
- **Sem resposta satisfatória no Brasil:** a Shopee Brasil participa em [consumidor.gov.br](https://www.consumidor.gov.br/pages/principal/), a plataforma pública brasileira onde as empresas aderentes se comprometem a responder até 10 dias (exige conta `gov.br` de nível prata ou ouro).

> A Shopee indica que só entra em contacto pelos canais oficiais, como a aplicação, contas verificadas nas redes sociais ou e-mails terminados em `shopee.com`, e recorda que os portes são sempre pagos na aplicação. Vê como identificar fraudes em [mensagens falsas de entregas](guide:delivery-scam-texts).

## Seguir uma encomenda SPX Express com o Peek

O [Peek](/) identifica de imediato a SPX Express Brazil a partir de um código `BR` com 12 algarismos e um algarismo ou letra final (com ou sem sufixo `SPXLM…`), apresentando «Transportadora detetada» com o botão «Alterar». Uma hiperligação de `spx.com.br` (`/track?` ou `/detail/` com o código `BR`) também associa logo a encomenda à SPX, mesmo colada de um SMS sem `https://`. O Peek apenas consulta a SPX com números `BR`, não com o `ID do pedido` da Shopee.

O Peek lê os mesmos dados de seguimento de `spx.com.br` e mostra o estado atual, as linhas da SPX (em português e algumas em inglês), a localidade registada nos centros logísticos, hubs, portos ou agências de levantamento e a hora exata da entrega convertida para o fuso horário do teu dispositivo — em `peektracker.com` e nas aplicações para iPhone e Android, o final do histórico indica «Horas no teu fuso horário: …». Revela ainda registos ocultos na página pública da SPX, como `Seu pedido chegou ao último centro logístico` ou uma tentativa de recolha sem sucesso no vendedor («Etiqueta criada»); quando uma recolha sem sucesso surge depois de `Pedido postado …`, o volume permanece no ponto de depósito («Aceite pela transportadora»), exceto se o registo indicar que o remetente não tinha preparado a encomenda («Etiqueta criada»). A SPX não disponibiliza data prevista de entrega, peso nem parceiro de distribuição. Numa ligação partilhada, a leitura de entrega mostra apenas «Entregue», sem o nome de quem recebeu a encomenda. Palavra-chave, PIN da *Agência Shopee* e reembolsos mantêm-se na aplicação da Shopee.

Enquanto a SPX ainda não reconhece o código, o Peek mostra «À espera de SPX Express Brazil» e continua a verificar. Se dois dias depois a SPX continuar sem conhecer o número e mais nenhuma fonte tiver respondido, o Peek apresenta «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e passa a consultá-lo uma vez por dia e, passada uma semana desde que o adicionaste, uma vez por semana. Se a encomenda já estiver em caminho e passarem quatro dias sem nova leitura, surge «Pede ajuda a SPX Express Brazil», que abre a página de horários do apoio ao cliente da Shopee.

Enquanto mantiveres a encomenda aberta no ecrã ou, no dia da entrega, com as notificações ativas, o Peek verifica atualizações até a cada 10 minutos — e até a cada 2 minutos a partir das 6h00 (hora local) no dia em que a SPX regista a saída para distribuição, enquanto o rastreador próprio da SPX responder (através apenas de serviços de seguimento externos: de hora a hora, a cada 3 horas após um dia sem novidades e até a cada 15 minutos no dia da entrega com a encomenda aberta ou notificações ligadas) —; fora dessas condições, bem como após dois dias sem novidades (ou 12 horas em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora a hora. Podes escolher alertas para «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a SPX nem a Shopee em teu nome.

## Perguntas frequentes sobre o seguimento SPX Express Brasil

### A SPX Express pertence à Shopee?

Sim: a Shopee apresenta a SPX Express como a sua logística própria e oficial no Brasil. O site `spx.com.br` é gerido pela SPX Express Brasil Ltda., com mais de 100 hubs logísticos e 11 centros de distribuição no país.

### Como seguir uma encomenda da SPX Express?

Na aplicação da Shopee no Brasil (`Eu` > `Minhas Compras` > a encomenda > `Status de Envio`), colando o código `BR` no Peek ou através da pesquisa em `spx.com.br`.

### O que significa Pedido em rota de entrega para seu endereço?

Significa que o volume saiu para distribuição rumo à tua morada no Brasil e que a SPX prevê entregá-lo nas próximas horas. Se a compra tiver palavra-chave (*palavra-chave*), tem-na pronta para indicar ao estafeta.

### Quanto tempo demora a SPX Express a entregar no Brasil?

A Shopee indica de 1 a 25 dias úteis após a recolha para encomendas de vendedores no Brasil e, em regra, de 30 a 50 dias seguidos para compras internacionais. A data prevista de cada encomenda aparece na respetiva página na aplicação.

### Posso contactar diretamente a SPX Express?

A Shopee esclarece que não para questões de seguimento ou entrega: o atendimento faz-se pelo `Chat Shopee` na aplicação, de segunda a sexta-feira das 8h00 às 22h00 e aos fins de semana das 8h00 às 20h00 (horário de Brasília), ou pelo formulário online.

:::sources
- [SPX Express: Página inicial](https://spx.com.br/br) – logística integrada da Shopee no Brasil, mais de 100 hubs e 11 centros de distribuição, SPX Express Brasil Ltda. e WhatsApp de parceiros/agências
- [SPX Express: Pesquisa de seguimento](https://spx.com.br/track) – página pública de consulta da SPX no Brasil
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – acompanhamento na aplicação da Shopee e cobertura no Brasil
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – mapa das agências e opção Retirar um pacote
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – logística própria e oficial da Shopee no Brasil
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – recolha, centros de distribuição e parceiros
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – modalidades de envio, ausência de contacto direto com a transportadora e 3 tentativas
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – códigos dos Correios
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – partilha automática do código de seguimento
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – data prevista e secção Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – identificador ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – entrega assegurada pelo vendedor
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – entrega pelos Correios em compras vindas do estrangeiro
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios ou Anjun Courier, documentos RG/CNH e formulário RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 a 25 dias úteis nacionais e 30 a 50 dias seguidos internacionais
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – sem marcação de horário, tentativas e reembolso automático
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – alteração única de morada antes da confirmação do pagamento
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – palavra-chave pedida na entrega
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – partilha com pessoa de confiança
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – não indicar a palavra-chave antes de receber o volume e recusar entregas trocadas
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – opção Retire Fácil, avisos, código PIN e prazo limite
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – levantamento com PIN e número de seguimento, guarda de 7 dias úteis e reencaminhamento
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – dias no camião entre estados e entrega até 5 dias após a data prevista
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – alterações de rota entre centros
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – motivos para um código ainda sem dados
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – pedido de reembolso e 2 dias de resposta para o vendedor
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – verificações e averiguação acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – prazo de 7 dias após a entrega na Entrega Padrão
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – acesso ao Chat Shopee na aplicação
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – horário de Brasília e formulário de pedidos
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – participação de conduta do estafeta
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – canais oficiais shopee.com e pagamento de portes sempre na aplicação
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – restrição por CPF irregular
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – reembolso internacional e impostos de importação não reembolsáveis
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – taxas PRC desde 12 de maio de 2026, ICMS de 17 % a 20 % e aviso sobre falsos boletos
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – pagamento dos impostos no fecho da encomenda
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee certificada no PRC
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – resposta até 10 dias e conta gov.br prata ou ouro
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – adesão da Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção de códigos BR e ligações spx.com.br, estados e frota da SPX, marcos ocultos e privacidade
:::
