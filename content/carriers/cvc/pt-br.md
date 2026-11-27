---
title: Rastreio CVC (Си Ви Си): acompanhe encomendas na Bulgária
description: Rastreie encomendas CVC (Си Ви Си, e-CVC) na Bulgária no Peek: como informar o código, escolher a CVC, entender os status em búlgaro e agir se parar.
slug: cvc-tracking
published: 2026-11-27
updated: 2026-11-27
---

A CVC é uma transportadora da Bulgária. O catálogo do Peek também a conhece como Си Ви Си, Си Ви Си ЕООД e e-CVC. Para rastrear uma encomenda da CVC, cole um link do rastreador da CVC na caixa acima, ou cole o código e escolha CVC como transportadora: o Peek não consegue identificar um código da CVC apenas pelo formato. O código vem da loja ou de quem enviou o pacote. O rastreador próprio da CVC fica em my.e-cvc.bg/track, em búlgaro: pede o número da guia (*waybill*) e traz apenas um botão, "Провери" (Consultar).

Os sites próprios da CVC informam pouco além disso: my.e-cvc.bg exibe somente esse rastreador e uma tela de login. Portanto, esta página explica o que o rastreador da CVC solicita e como o Peek acompanha encomendas da CVC, sem listar regras de entrega, tarifas ou contatos da empresa.

## Formato do código de rastreio CVC

O rastreador da CVC pede o número do conhecimento de transporte ("товарителница" em búlgaro). O campo aceita apenas dígitos: remove letras e espaços conforme você digita e permite no máximo 20 caracteres. Assim, um código CVC é formado por números, mas o catálogo do Peek não possui um padrão específico para a CVC: ele não reconhece o número como da CVC, e a CVC nunca aparece em "Compatível com este código" ao adicionar. Esta página não traz um exemplo porque o rastreador da CVC não exibe como é um número completo.

| O que você cola | O que o Peek faz |
| --- | --- |
| Apenas o código CVC | Não identifica a CVC sozinho. Pode sugerir outras transportadoras compatíveis com aquele formato, ou nenhuma. Escolha a CVC você mesmo, como explicado abaixo em "Acompanhe sua encomenda CVC no Peek". |
| Link do rastreador CVC: `my.e-cvc.bg/track?wb=` seguido do código | O Peek extrai o número do link e identifica CVC na hora ("Transportadora detectada"). |
| Código que a loja diz ser de outra transportadora | Rastreie com essa transportadora: o e-mail da loja é a melhor indicação de quem entrega. |

**Onde encontrar.** No e-mail de envio da loja ou na página do pedido, às vezes chamado de *waybill number*. Se foi enviado por uma pessoa, peça o número a ela. Não tem nenhum código? Veja [como encontrar um código de rastreio](guide:find-tracking-number) e [formatos de código de rastreio](guide:tracking-number-formats) para conhecer os padrões de outras transportadoras.

## Significado dos status CVC no Peek

O Peek lê o rastreador da própria CVC e mostra cada linha no texto original em búlgaro, classificando-a em uma das etapas do Peek. Veja as mensagens conhecidas pelo código do Peek:

| A CVC exibe | O Peek mostra | O que significa para você |
| --- | --- | --- |
| `Приета за събиране` | "Etiqueta criada" | O envio foi agendado para coleta. A CVC ainda não está com o pacote. |
| `Отложено събиране за …` | "Etiqueta criada" | A coleta foi adiada para a data indicada na mensagem. |
| `Взета от куриер …` | "Recebida pela transportadora" | Um entregador da CVC coletou a encomenda. |
| `Приета в Хъб …`, `Транспорт към Хъб …` | "Em trânsito" | O pacote chegou a um centro da CVC ou está a caminho de um. |
| `Приета за разнос` | "Saiu para entrega" | Está em rota de entrega. |
| `Доставена`, `Доставена на …` | "Entregue" | Entregue a você ou a quem recebeu em seu nome. |

Qualquer mensagem não mapeada mantém o status anterior exibindo as palavras originais da CVC. Quando o rastreador da CVC não reconhece o código, responde `Пратката не е открита!` (envio não encontrado). Se o rastreador da CVC falhar, o Peek também pode consultar serviços de rastreamento, como ParcelsApp, Ship24 e 17TRACK, exibindo o histórico do primeiro que localizar o pacote, nos termos desse serviço.

Antes de a CVC retornar alguma linha, o Peek mostra "Buscando atualizações" enquanto consulta e, em seguida, "Aguardando CVC". Se duas verificações seguidas falharem, o pacote continua em "Aguardando CVC" e a página diz "Ainda não foi possível acessar CVC. O Peek continua tentando." Saiba mais em [significado dos status de rastreio](guide:tracking-statuses).

## Entrega CVC na Bulgária

Os sites próprios da CVC não publicam prazos, número de tentativas, agências, regras de pagamento na entrega nem prazos de retirada: my.e-cvc.bg mostra apenas o rastreador e o login. Pergunte à loja ou ao remetente como e quando a CVC entrega, pois foram eles que contrataram o serviço. Se a entrega falhar ou ficar aguardando retirada, veja [entrega não realizada](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Encomenda CVC sem atualização, perdida ou devolvida

**Nada ainda.** O rastreador da CVC pode ainda não conhecer o número. O Peek mostra "Aguardando CVC" e continua tentando. Se após dois dias da primeira resposta negativa a CVC ainda não reconhecer o código, o Peek pede para conferir o número, trocar de transportadora ou excluir a encomenda. Confira os dígitos no e-mail da loja. Se nada aparecer depois de alguns dias, abra o rastreador da própria CVC com "Abrir o site de CVC" na página do pacote e pergunte à loja se o envio foi realmente feito.

**Sem novidades há dias.** Após quatro dias sem nova atualização, uma encomenda ainda em "Etiqueta criada", "Recebida pela transportadora" ou "Em trânsito" recebe o aviso com a data do último registro: "Sem atualizações desde seg 12 out". Veja [por que o rastreio para de atualizar](guide:tracking-not-updating).

**Entregue, mas nada chegou.** Confira perto da porta e com vizinhos e depois fale com a loja. Veja [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou danificada.** Avise a loja primeiro: foi ela quem contratou a entrega e pode cobrar a CVC ou enviar um novo pacote. Veja [encomendas perdidas](guide:lost-parcel) e [encomendas danificadas](guide:damaged-parcel).

**Devolvida.** "Devolvida ao remetente" indica que o pacote retornou à loja ou a quem o enviou. Solicite reembolso ou reenvio a eles. Veja [devolvida ao remetente](guide:returned-to-sender).

## Contato e atendimento ao cliente CVC

Os sites da própria CVC não listam telefone, e-mail nem endereço de agência: my.e-cvc.bg mostra apenas o rastreador e uma tela de login. O Peek publica somente contatos lidos diretamente nas páginas da própria transportadora, por isso esta página não informa nenhum. Como a loja ou o remetente contratou a CVC, fale com eles primeiro levando o número do pedido e o código de rastreio.

## Acompanhe sua encomenda CVC no Peek

O [Peek](/) acompanha mais de 4.500 transportadoras — entre elas a CVC — mas não identifica um pacote CVC somente pelo número. Um link do rastreador da CVC identifica a transportadora imediatamente; se tiver apenas o código, escolha a transportadora você mesmo:

1. Cole o código na caixa acima.
2. Toque em "Trocar" ao lado da linha da transportadora e busque por CVC. Buscar "e-CVC" ou "Си Ви Си" também encontra a CVC, com "Também chamada de e-CVC" ou "Também chamada de Си Ви Си" abaixo do nome; "Bulgaria" também.
3. Escolha a CVC: a linha passa a mostrar "Escolhida por você". Acompanhe a encomenda.

Em um pacote já adicionado, use "Trocar transportadora". Para a CVC, o Peek aceita de 1 a 20 dígitos; para qualquer outro formato, avisa que a CVC "não emite códigos como este". Se o Peek listar outras transportadoras em "Compatível com este código", isso significa apenas que o formato coincide. Antes da seleção, a linha pode mostrar "ainda não encontrada · o Peek continua verificando", algo normal em códigos novos.

Se você deixar a escolha com o Peek, ele consulta serviços de rastreamento. Caso algum mostre movimentação e aponte a CVC como transportadora, o Peek associa o pacote à CVC sozinho.

**O que você vê.** O status e cada registro do rastreador da CVC com data e hora lidas no horário da Bulgária. O rastreador da CVC não fornece local, data prevista, ponto de retirada nem peso. "Abrir o site de CVC" abre o código diretamente no rastreador da CVC em búlgaro; se o histórico vier de um serviço de rastreamento, o link abre a página desse serviço, como "Abrir o site de ParcelsApp".

**Outra transportadora.** Se o rastreador da CVC não conhecer o código, o Peek também consulta, nos primeiros 30 dias da encomenda, outras transportadoras cujo formato combine com seu número. Se alguma mostrar o pacote em movimento com esse código, o Peek passa a encomenda para ela e mostra "Transportadora trocada automaticamente (antes: CVC)".

**Frequência de verificação.** Enquanto o pacote estiver aberto no Peek, ou com notificações ativadas no dia em que a CVC registrar `Приета за разнос`, o Peek consulta até a cada 10 minutos e, nesse dia, a cada 2 minutos a partir das 6h no horário da Bulgária. Após dois dias sem novidades (12 horas enquanto ainda mostrar "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora, e a cada 6 horas após sete dias sem novidades em "Etiqueta criada". As notificações podem acompanhar "Cada movimentação", "Etapas importantes" ou "Só a entrega".

O Peek não pode entrar em contato com a CVC, a loja ou o remetente por você, nem alterar entregas ou fazer pedidos de indenização.

## Perguntas sobre o rastreio CVC

### Como faço para rastrear uma encomenda da CVC?

Cole um link do rastreador da CVC na caixa acima para o Peek reconhecer a CVC sozinho. Com apenas o código, toque em "Trocar" e escolha CVC, já que o Peek não reconhece um código da CVC pelo formato; depois disso, o Peek acompanha pelo rastreador próprio da CVC. Em pacotes já salvos, use "Trocar transportadora". O site da CVC fica em my.e-cvc.bg/track: informe o número da guia e clique em "Провери".

### Como é o código de rastreio da CVC?

É um número de guia ("товарителница") composto apenas de dígitos: o campo do site da CVC remove letras e espaços e aceita até 20 caracteres. O catálogo do Peek não tem formato para ele, então o Peek não o reconhece como da CVC: escolha a CVC você mesmo.

### e-CVC é a mesma empresa que CVC?

Sim. O rastreador da CVC em my.e-cvc.bg chama-se e-CVC.bg e exibe o logotipo da CVC, e o catálogo do Peek lista e-CVC, Си Ви Си e Си Ви Си ЕООД como nomes alternativos da CVC. Buscar qualquer um deles no Peek encontra a CVC.

### Por que minha encomenda CVC mostra "Aguardando CVC"?

No peektracker.com, um pacote atribuído à CVC mostra isso enquanto o rastreador da CVC ainda não tem registro para o código ou não pôde ser acessado; nesse caso a página diz "Ainda não foi possível acessar CVC. O Peek continua tentando." O Peek continua consultando. Confira o número com a loja e consulte o rastreador da própria CVC com "Abrir o site de CVC".

### Qual é o telefone da CVC?

Esta página não informa nenhum telefone porque os sites da própria CVC não publicam telefone. Fale com a loja que enviou a encomenda, pois ela contratou a entrega com a CVC.

:::sources
- [CVC: e-CVC.bg tracking](https://my.e-cvc.bg/track) – rastreador em búlgaro, "Потърсете товарителница", dígitos, até 20 caracteres, "Провери", número após `wb=`, nome e-CVC.bg, logotipo CVC
- [CVC: e-CVC.bg login](https://my.e-cvc.bg/) – apenas página de login, sem páginas de entrega, preços ou contato
- [Universal Parcel Scraper: CVC](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/cvc) – nomes e nomes alternativos, Bulgária, endereço do rastreador, regra de link, ausência de formato próprio, leitura direta do rastreador CVC, mensagens em búlgaro, horário da Bulgária
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – serviços de rastreamento, associação do nome de transportadora à CVC, links para o site da transportadora ou do serviço
:::
