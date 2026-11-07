---
title: Rastreio SPX Express: pedidos da Shopee, status e prazos
description: Rastreie seu pedido da Shopee pela SPX Express: onde achar o código de rastreio BR, o que cada status significa, Agência Shopee e reembolso.
slug: rastreio-spx-express
published: 2026-11-01
updated: 2026-11-04
---

A SPX Express (Shopee Xpress) é a logística própria da Shopee no Brasil. Ela coleta os pacotes com os vendedores e entrega na sua casa ou em uma Agência Shopee, um comércio parceiro onde você retira o pedido. A Shopee chama o serviço de Entrega Padrão e orienta a acompanhar tudo pelo app, onde ficam o pedido, a data prevista e o eventual reembolso. O site spx.com.br também tem uma busca de rastreio; ou cole o código no campo acima.

## Código de rastreio SPX Express: onde encontrar

No app da Shopee, vá em "Eu" > "Minhas Compras", escolha o pedido em "A Caminho" (alguns artigos da Shopee dizem "A Receber") e toque em "Status de Envio" para ver cada atualização. A Shopee mostra o código de rastreio ali automaticamente assim que ele estiver disponível, junto com a data prevista de entrega.

Os formatos que o Peek conhece (dígitos inventados):

| Formato | O que é |
| --- | --- |
| `BR0000000000000` | SPX Express: `BR`, 12 dígitos e um dígito final. |
| `BR000000000000X` | SPX Express: `BR`, 12 dígitos e uma letra final. |
| `BR000000000000XSPXLM00000000` | SPX Express: um desses códigos seguido de `SPXLM` e 8 dígitos, como na etiqueta de um marketplace. |
| `EA000000005BR` | Duas letras, nove dígitos e `BR`: um código dos Correios, não da SPX. |

O "ID do pedido" não é código de rastreio, mas a Shopee pode pedi-lo no Atendimento ao Cliente.

Nem todo pedido da Shopee vai pela SPX. A Shopee lista a Entrega Padrão (Shopee Xpress), a Entrega Correios, cujo código também funciona no site dos Correios, e a Entrega do Vendedor, em que o próprio vendedor entrega e atualiza o status. Para compras internacionais, a Shopee cita os Correios, com um código de rastreio local no app, ou a parceira Anjun Courier. Mesmo assim, o rastreio da SPX traz status de alguns pacotes do exterior (veja abaixo).

## O que significam os status da SPX

O rastreio da SPX vem em português; alguns status terminam com o local depois dos dois-pontos.

| Status da SPX | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Pedido em preparação` | O vendedor está preparando o pedido. | "Etiqueta criada" |
| `Pedido postado …` | O vendedor deixou o pacote em um ponto de postagem (no site da SPX, a cidade vem em seguida); a SPX coleta o pacote ali. | "Recebida pela transportadora" |
| `Seu pedido foi coletado` | A SPX já está com o pacote. | "Recebida pela transportadora" |
| `Seu pedido chegou ao centro logístico: …` | Chegou a um centro logístico. | "Em trânsito" |
| `Seu pedido saiu do centro logístico: …` | Saiu de um centro logístico. | "Em trânsito" |
| `O pedido está em separação no centro logístico, novas atualizações em até 1 dia.` | Está na triagem; a SPX avisa que a próxima atualização sai em até 1 dia. | "Em trânsito" |
| `Pedido em trânsito` | Está em transferência entre centros. Entre estados ou cidades distantes, diz a Shopee, o pacote passa dias dentro do caminhão de transferência, e o status só muda quando ele é descarregado e bipado no centro de distribuição do seu estado. | "Em trânsito" |
| `Pedido em rota de entrega para seu endereço, aguarde pelas próximas horas.` | Saiu para entrega e deve chegar nas próximas horas. | "Saiu para entrega" |
| `Houve um imprevisto durante a entrega do seu pedido …`, `Um imprevisto ocorreu …` | A entrega não pôde ser concluída naquele dia; a SPX diz que vai tentar de novo no seu endereço o quanto antes. Veja as tentativas abaixo. | "Tentativa de entrega" |
| `Pedido entregue` | A SPX registrou a entrega. | "Entregue" |
| `Pedido será devolvido ao vendedor` | O pacote vai voltar para o vendedor. | "Devolvida ao remetente" |
| `Pedido devolvido` | O pacote voltou para o vendedor. | "Devolvida ao remetente" |

Alguns pacotes recebem, em vez disso, status da frota de entrega da SPX, que começam com `Seu pacote`, entre eles `Seu pacote foi recebido pelo centro de triagem`, `Seu pacote foi recebido pelo centro de distribuição` (o centro que entrega na sua região), `Seu pacote está sendo entregue pelo entregador` e `Seu pacote foi entregue`. O Peek mostra as mesmas etapas para eles, como "Saiu para entrega".

**Pacotes do exterior.** Os status que começam com `[China]` são atualizações feitas na China, como `[China]O pedido foi liberado na alfândega de exportação`. Depois vêm `Pedido chegou no Brasil`, `Pedido está na alfândega de importação`, `Pedido em inspeção na alfândega de importação` e, em inglês, `Import Customs Cleared`: a alfândega brasileira liberou o pacote. O Peek mostra "Na alfândega" nos dois status da alfândega e volta a "Em trânsito" depois de `Import Customs Cleared`.

Um status é má notícia: `O pacote não pôde ser importado, será retornado ou confiscado pela alfândega`. A alfândega recusou a importação, e o pacote vai voltar ou ser apreendido; o Peek mostra "Precisa de atenção". Fale com a Shopee: o reembolso de compras internacionais segue o mesmo passo a passo das nacionais (veja abaixo).

## Entrega SPX: tentativas, palavra-chave e Agência Shopee

**Prazo.** Nem você nem o vendedor podem agendar dia ou hora da entrega. A Shopee fala em 1 a 25 dias úteis para pedidos de vendedores nacionais, contados a partir da coleta, e geralmente 30 a 50 dias corridos para pedidos internacionais.

**Tentativas.** Os parceiros logísticos da Shopee fazem 3 tentativas de entrega antes de devolver o pedido ao vendedor; se nenhuma der certo, o reembolso é automático. O endereço só pode ser alterado antes da confirmação do pagamento, e uma única vez. Depois disso, a Shopee orienta a esperar as tentativas acabarem e receber o reembolso.

**Palavra-chave.** Alguns pedidos têm uma senha aleatória que você informa ao entregador na porta; só quem sabe a senha recebe o pacote. Ela aparece na página do pedido e chega por e-mail e notificação da Shopee. Se você não estiver em casa, passe a palavra-chave para alguém de confiança, como um vizinho ou o porteiro do prédio. Nunca a informe antes de estar com o pacote na mão, e recuse uma entrega feita para outro nome ou endereço.

**Retirada na Agência Shopee ("Retire Fácil").** Onde o serviço já está disponível, escolha ao finalizar a compra, em "Opção de Envio" > "Ver mais" > "Retire Fácil"; depois não dá para trocar para entrega em casa. Quando o pacote chega à agência, a Shopee avisa por WhatsApp, e-mail ou no app, com um código PIN; a página do pedido também mostra a data máxima para retirada. No balcão, mostre o PIN e o código de rastreio; qualquer pessoa com os dois pode retirar por você. O prazo é de 7 dias úteis; depois, a SPX recolhe o pacote. Se a agência ficar temporariamente indisponível, a Shopee pode mandar o pacote para o seu endereço padrão. O [mapa da SPX](https://spx.com.br/service-point) mostra as agências: escolha "Retirar um pacote".

## Compras do exterior: Remessa Conforme e taxação

A Receita Federal lista a Shopee entre as empresas certificadas no Programa Remessa Conforme (PRC). Em site certificado, o imposto de importação e o ICMS são pagos na finalização da compra. Confira se os impostos aparecem na tela de fechamento do pedido: se não aparecerem, a Receita avisa que eles serão cobrados na chegada da mercadoria e você perde a redução do imposto de importação.

As alíquotas na página da Receita (atualizada em 12 de junho de 2026):

- **Imposto de importação** em site certificado, para encomenda destinada a pessoa física, em declarações registradas a partir de 12 de maio de 2026: 0% até US$ 50 de valor aduaneiro (soma dos bens, frete e seguro); acima de US$ 50, 60%, com desconto do equivalente a US$ 30 sobre o imposto a pagar.
- **ICMS**, o imposto estadual: hoje de 17% a 20%, conforme o estado, com site certificado ou não.

Segundo a Shopee, um CPF irregular pode impedir a compra internacional, e os impostos de importação não são reembolsados. Se a alfândega segurar um pedido entregue pela Anjun Courier, a Shopee pede comprovantes do pagamento e do valor da compra, cópia do RG ou da CNH e o formulário para comprovação de valor (RCV). Se o pacote vier pelos Correios, veja no rastreio deles se há alguma pendência. A Receita não envia boletos, não liga e não manda e-mails cobrando esses impostos.

## Pacote SPX sem atualização, atrasado ou não recebido

**Dias sem atualização.** A Shopee diz que é comum: o rastreio só muda quando o pacote chega a um novo centro e é bipado, então viagens longas entre estados, fins de semana e fila no hub deixam o status parado. Uma rota que parece se afastar de você geralmente faz parte do plano da transportadora. Na maioria das vezes, o rastreio dá um "salto" e mostra várias etapas de uma vez quando o pacote entra na rota final. Veja o guia sobre [rastreio que não atualiza](guide:tracking-not-updating) (em português de Portugal).

**Código de rastreio não funciona.** O pacote pode ainda não ter sido coletado, ou o código pode estar errado ou ainda não ter entrado no sistema. Pergunte ao vendedor pelo chat da Shopee; segundo a Shopee, isso não quer dizer que a encomenda foi perdida.

**Atrasado.** Espere passar a data prevista. A partir daí, você pode pedir reembolso: "Eu" > "A Caminho" > o pedido > "Pedido de Reembolso", com o motivo "Não recebi o pedido". O vendedor tem 2 dias para responder; se não responder, o reembolso sai automaticamente. A Shopee diz que a maioria dos pedidos atrasados chega em até 5 dias depois da data prevista.

**Entregue, mas não recebido.** Pergunte a vizinhos, familiares e na portaria ou recepção do prédio; depois, peça o reembolso no app: na Entrega Padrão, em até 7 dias a partir da entrega. A Shopee pode pedir que a transportadora investigue (a "acareação"): ela pode falar com você por WhatsApp ou e-mail, ou o entregador pode voltar ao endereço.

**Devolvido.** Depois de `Pedido será devolvido ao vendedor`, o pacote está voltando para o vendedor. Se foi porque a entrega não deu certo, a Shopee faz o reembolso automaticamente.

## Contato da SPX Express: fale com a Shopee

Segundo a Shopee, não dá para falar direto com a transportadora sobre rastreio ou entrega: quem resolve é a própria Shopee, pela Garantia Shopee. Informações consultadas em 26 de outubro de 2026:

- **Chat Shopee**: no app, "Eu" > "Central de Ajuda", role até "Atendimento ao Cliente" e escolha "Chat Shopee". De segunda a sexta, das 8h às 22h; sábados e domingos, das 8h às 20h, horário de Brasília.
- **Formulário de solicitações**: a qualquer hora, pela [página de horários do atendimento](https://help.shopee.com.br/portal/4/article/141507). Tenha o ID do pedido em mãos.
- **Entregador com má conduta**, por exemplo pedindo dinheiro a mais: reporte pelo Chat Shopee.
- **Ainda sem solução?** A Shopee Brasil está no [consumidor.gov.br](https://www.consumidor.gov.br), a plataforma de reclamações do governo. As empresas participantes se comprometem a responder em até 10 dias. Você precisa de uma conta gov.br nível prata ou ouro.

> A Shopee só entra em contato por canais oficiais, como o app, contas verificadas em redes sociais ou e-mails que terminam em shopee.com. O frete é sempre pago no app: mensagem pedindo pagamento de frete por fora é golpe. Veja o guia sobre [mensagens falsas de entrega](guide:delivery-scam-texts) (em português de Portugal).

## Rastrear um pacote SPX Express com o Peek

Com um código `BR` de 12 dígitos e um dígito ou letra final, o [Peek](/) já indica a SPX Express Brazil como transportadora. Um link do spx.com.br (`/track?` ou `/detail/` com o código) também indica a SPX, mesmo quando vem em um SMS sem o `https://`. O Peek só consulta a SPX com códigos `BR`, não com o ID do pedido da Shopee.

O Peek lê o mesmo rastreio do spx.com.br e mostra o status, as mensagens da SPX (em português, algumas em inglês), o local de cada registro em centros logísticos, hubs e portos e a hora da entrega. Também mostra algumas atualizações que a página da SPX esconde, como `Seu pedido chegou ao último centro logístico` ou uma tentativa de coleta sem sucesso no vendedor. Não há data prevista, peso, ponto de retirada nem parceiro de entrega, e o nome de quem recebeu o pacote fica de fora. Pedido, palavra-chave, PIN da Agência e reembolso ficam no app da Shopee.

Enquanto a SPX ainda não conhece o código, o Peek mostra "Aguardando a transportadora" e continua consultando. Se o rastreio já começou e o pacote passa 4 dias em trânsito sem atualização nova, aparece o link "Pedir ajuda a SPX Express Brazil", que abre a página de horários do Atendimento ao Cliente da Shopee.

O Peek consulta a SPX a cada 10 minutos e a cada 2 minutos quando o pacote sai para entrega: só nesse dia, a partir das 6h no horário local e enquanto o rastreio da própria SPX responder. Por serviços de rastreamento, a consulta é a cada 15 minutos. Você escolhe os avisos: "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não fala com a SPX nem com a Shopee por você.

## Perguntas sobre o rastreio SPX Express

### A SPX Express é da Shopee?

Sim: a Shopee chama a SPX Express de sua logística própria e oficial no Brasil. A empresa por trás do spx.com.br é a SPX Express Brasil Ltda., com mais de 100 hubs logísticos e 11 centros de distribuição.

### Como rastrear uma encomenda da SPX Express?

No app da Shopee: "Eu" > "Minhas Compras" > o pedido > "Status de Envio". Você também pode colar o código `BR` no Peek ou usar a caixa de rastreio do spx.com.br.

### O que significa "Pedido em rota de entrega para seu endereço"?

O pacote saiu para entrega no seu endereço, e a SPX pede para aguardar nas próximas horas. Se o pedido tiver palavra-chave, tenha a senha em mãos para o entregador.

### Quanto tempo a SPX Express demora para entregar?

A Shopee fala em 1 a 25 dias úteis a partir da coleta para pedidos de vendedores nacionais, e geralmente 30 a 50 dias corridos para os internacionais. A data prevista está na página do pedido.

### Como falar com a SPX Express?

Pela Shopee, que diz não ser possível falar direto com a transportadora: use o Chat Shopee, de segunda a sexta das 8h às 22h e nos fins de semana das 8h às 20h, horário de Brasília.

:::sources
- [SPX Express: Página inicial](https://spx.com.br/br) – logística integrada da Shopee no Brasil, hubs e centros de distribuição, caixa de rastreio, busca de Agências Shopee, SPX Express Brasil Ltda.
- [SPX Express: Sobre Nós](https://spx.com.br/br/help-center/about-us.html) – rastreio no app da Shopee
- [SPX Express: Encontre uma Agência Shopee](https://spx.com.br/service-point) – mapa das agências, "Retirar um pacote"
- [Shopee: O que é a SPX Express?](https://help.shopee.com.br/portal/4/article/197188) – logística própria e oficial da Shopee no Brasil
- [Shopee: O que é Shopee Xpress (SPX)?](https://help.shopee.com.br/portal/4/article/76163) – nome, coleta, centro de distribuição, parceiros
- [Shopee: Posso entrar em contato com os parceiros logísticos da Shopee?](https://help.shopee.com.br/portal/4/article/76264) – tipos de entrega, rastreio no app, sem contato direto com a transportadora, 3 tentativas
- [Shopee: Como faço para verificar o status do meu pedido?](https://help.shopee.com.br/portal/4/article/76323) – códigos dos Correios
- [Shopee: Quais são as opções de envio disponíveis na Shopee?](https://help.shopee.com.br/portal/4/article/76335) – código de rastreio compartilhado automaticamente
- [Shopee: Como eu acho a data prevista de entrega para o meu pedido?](https://help.shopee.com.br/portal/4/article/81887) – data prevista, Status de Envio
- [Shopee: Como encontro o ID do pedido da minha compra?](https://help.shopee.com.br/portal/4/article/76194) – ID do pedido
- [Shopee: O que é logística do vendedor?](https://help.shopee.com.br/portal/4/article/132039) – entrega pelo vendedor e link de rastreio
- [Shopee: Como minha encomenda do exterior será entregue quando chegar ao Brasil?](https://help.shopee.com.br/portal/4/article/76363) – Correios para compras do exterior
- [Shopee: Por que meu pedido está parado na alfândega do Brasil?](https://help.shopee.com.br/portal/4/article/153683) – Correios ou Anjun Courier, documentos, RCV
- [Shopee: Quando receberei meu pedido?](https://help.shopee.com.br/portal/4/article/76349) – 1 a 25 dias úteis, 30 a 50 dias corridos
- [Shopee: Posso agendar a data e hora de entrega do meu pedido?](https://help.shopee.com.br/portal/4/article/76333) – sem agendamento, tentativas, reembolso automático
- [Shopee: Posso alterar meu endereço de entrega depois que meu pedido for feito?](https://help.shopee.com.br/portal/4/article/76279) – mudança de endereço
- [Shopee: O que é a palavra-chave no meu pedido?](https://help.shopee.com.br/portal/4/article/127204) – palavra-chave, 3 tentativas, reembolso
- [Shopee: O que fazer quando não estiver em casa para informar a palavra-chave?](https://help.shopee.com.br/portal/4/article/127206) – pessoa de confiança
- [Shopee: O que fazer se minha palavra-chave não funcionar corretamente?](https://help.shopee.com.br/portal/4/article/127205) – recusar uma entrega errada
- [Shopee: Retirada de pacotes na Agência Shopee](https://help.shopee.com.br/portal/4/article/164642) – Retire Fácil, notificações, PIN, data máxima para retirada
- [Shopee: Sobre a Agência Shopee](https://help.shopee.com.br/portal/4/article/164639) – PIN e código de rastreio, 7 dias úteis, a SPX recolhe o pacote, sem troca para entrega em casa, redirecionamento
- [Shopee: Meu pedido está sem atualização no rastreamento](https://help.shopee.com.br/portal/4/article/223973) – sem atualização diária, dias no caminhão de transferência entre estados, "salto", 5 dias após a data prevista
- [Shopee: Por que meu pedido está parado ou mudou de rota?](https://help.shopee.com.br/portal/4/article/159363) – mudanças de rota
- [Shopee: Por que meu código de rastreio não está funcionando?](https://help.shopee.com.br/portal/4/article/76369) – motivos, falar com o vendedor
- [Shopee: O que devo fazer se não receber meu pedido até a data prevista de entrega?](https://help.shopee.com.br/portal/4/article/76321) – passo a passo do reembolso, 2 dias para o vendedor
- [Shopee: O que devo fazer se não receber um pedido, mas o status está como Entregue?](https://help.shopee.com.br/portal/4/article/76207) – verificações, acareação
- [Shopee: Como faço para solicitar devolução/reembolso?](https://help.shopee.com.br/portal/4/article/76204) – 7 dias a partir da entrega
- [Shopee: Como entro em contato com o Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/78354) – Chat Shopee, horários
- [Shopee: Qual horário do Atendimento ao Cliente da Shopee?](https://help.shopee.com.br/portal/4/article/141507) – horários, formulário de solicitações
- [Shopee: O entregador do meu pedido teve uma má conduta](https://help.shopee.com.br/portal/4/article/224511) – relatar um entregador
- [Shopee: Como faço para detectar um golpe?](https://help.shopee.com.br/portal/4/article/76330) – canais oficiais, frete pago no app
- [Shopee: Por que não consigo concluir minha compra internacional?](https://help.shopee.com.br/portal/4/article/141561) – CPF irregular
- [Shopee: Como solicitar devolução/reembolso ou realizar o cancelamento da minha compra internacional?](https://help.shopee.com.br/portal/4/article/134867) – mesmo passo a passo, impostos de importação não reembolsáveis
- [Receita Federal: Tributação](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/topicos/tributacao) – alíquotas a partir de 12/05/2026, ICMS, pagamento, golpes
- [Receita Federal: Em que momento vou pagar os impostos?](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/preciso-pagar-impostos-nas-compras-internacionais/em-que-momento-pagarei-o-imposto) – impostos na finalização da compra
- [Receita Federal: Empresas certificadas no Programa Remessa Conforme](https://www.gov.br/receitafederal/pt-br/assuntos/aduana-e-comercio-exterior/manuais/remessas-postal-e-expressa/empresas-certificadas-no-programa-remessa-conforme-prc) – Shopee certificada
- [Consumidor.gov.br: Início](https://www.consumidor.gov.br/pages/principal/) – 10 dias, nível da conta gov.br
- [Consumidor.gov.br: Empresas participantes](https://www.consumidor.gov.br/pages/principal/empresas-participantes) – Shopee Brasil
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – status da SPX, formatos de código, links, o que o Peek mostra, link de ajuda
:::
