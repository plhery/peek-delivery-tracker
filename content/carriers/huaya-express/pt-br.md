---
title: Rastreamento Huaya Express: códigos HYYT e onde param
description: A Huaya Express (华亚运通) envia pedidos online de Guangzhou: como ler códigos HYYT…GZ, tradução das etapas e quem procurar após o repasse.
slug: rastreamento-huaya-express
published: 2026-11-26
updated: 2026-11-26
---

Huaya Express (华亚运通) é a marca estampada no logotipo da 华亚运通国际货运代理有限公司 (Huaya Yuntong International Freight Forwarding), uma agente de cargas em Guangzhou, na China, que transporta encomendas ao exterior para lojas online. Um conhecimento da Huaya começa com HYYT e termina com GZ. Cole-o na caixa acima ou clique no link 物流跟踪 (rastreamento logístico) no menu superior de hyytgz.com para abrir o próprio rastreador da Huaya. O histórico da Huaya termina quando ela repassa o pacote a uma transportadora no seu país — ou após algumas leituras dessa transportadora (nos EUA, às vezes até a entrega) — sem nunca informar qual empresa é, exceto em uma rota pela DHL na Alemanha (em que copia leituras da DHL), em uma rota para o México (em que copia leituras da J&T Express) e em uma rota sueca que cita a Early Bird.

## Código de rastreamento Huaya Express: como ele é

A Huaya não publica formato de código. O padrão reconhecido pelo Peek é HYYT, doze dígitos e GZ. Exemplos, todos inventados:

| Código | O que é |
| --- | --- |
| `HYYT482910573617GZ` | HYYT, doze dígitos e GZ: formato de um conhecimento da Huaya Express. |
| `HYYT48291057317GZ` | Onze dígitos: um dígito se perdeu ao copiar. Copie novamente da mensagem do vendedor. |
| `RR482910576CN` | Número postal terminado em CN: quem rastreia é a [China Post](carrier:china-post), não a Huaya. |

O Peek aceita o código com ou sem espaços, em maiúsculas ou minúsculas.

**Como obter o código.** A Huaya atende lojistas: sua página inicial cita lojas Shopify, Vova, DHgate (敦煌) e vendedores no Facebook. O código vem da loja onde você comprou; se não chegou, veja [como encontrar seu código de rastreamento](guide:find-tracking-number). O rastreador da Huaya também aceita a referência de pedido da loja, digitada exatamente como ela escreveu, incluindo hifens.

**Link de rastreamento com endereço IP.** O rastreador da Huaya não fica em hyytgz.com, mas em um endereço numérico sobre HTTP simples. Cole um link dele contendo `trackIndex.htm`, `documentCode=` e seu código diretamente no Peek. Já o link 物流跟踪 no rodapé do site abre o 17TRACK, um serviço externo.

## Status de rastreamento da Huaya Express explicados

O rastreador da Huaya possui versão em chinês e em inglês. As linhas conhecidas pelo Peek aparecem em inglês, exceto a entrada no armazém e algumas linhas posteriores sobre aeroporto, voo, alfândega e repasse (que podem aparecer em chinês) e uma linha na Suécia em sueco. Na ordem habitual:

| A Huaya mostra | O que significa para você |
| --- | --- |
| `Shipping Label Created` | A etiqueta da transportadora que entregará no seu país existe: apenas dados do pedido, a Huaya ainda não está com o pacote. |
| `Shipment not yet received or processed` | Também é linha da transportadora entregadora repassada antes de o pacote chegar ao ponto de coleta da Huaya: apenas dados do pedido, ninguém está com o pacote ainda. |
| `Package received & scanned at warehouse`, `到达收货点` | A Huaya recebeu o pacote em seu armazém de Guangzhou: primeira leitura em suas mãos. A linha chinesa significa chegada ao ponto de recebimento. |
| `Package departed from the warehouse` | Saiu desse armazém. |
| `Customs export clearance completed` | A China continental liberou a exportação. A inspeção de importação no seu país vem depois. |
| `Package handed over to airline` | Entregue à companhia aérea para o trecho internacional. |
| `Shipment Announced` | A Huaya notificou a transportadora que entregará no seu país; essa empresa ainda não recebeu o pacote. |
| `Package in transit to destination country` | Em deslocamento rumo ao seu país. |
| `Package arrived at destination country` | Chegou ao seu país. |
| `Customs import clearance completed, awaiting pickup by local carrier` | A alfândega do seu país liberou o pacote; falta a transportadora local coletá-lo. |
| `It has been handed over to the service provider.` | A transportadora local coletou o pacote. A Huaya não informa o nome nem o código dela, e as linhas conhecidas depois desta pertencem a uma transportadora nos EUA: `Arrived at Origin Hub` (primeiro hub, perto do aeroporto O'Hare de Chicago), depois `Departed Origin Hub`, `Arrived at Destination Hub` (hub mais próximo de você), `Departed Destination Hub`, `Arrived At Warehouse` (armazém antes da rota), `Out for Delivery` (rota de entrega — "Saiu para entrega" no Peek) e `Delivered`, na sua cidade ("Entregue" no Peek). |
| `Din leverans ?r bokad med Early Bird.` | Em sueco, "Sua entrega foi agendada com a Early Bird": na Suécia, a Early Bird fará a entrega. O rastreador da Huaya imprime o `ä` de `är` como `?`. |

Os horários da Huaya vêm sem fuso: seguem o horário de Pequim, exceto nas leituras copiadas da DHL e nas leituras da transportadora americana em cidades dos EUA (que mantêm o horário do local da leitura), e nas leituras copiadas da J&T Express para o México (horário da Cidade do México). Se o rastreador não tiver dados para um código, a coluna 参考号 (referência) repete o código e o resto da linha fica vazio.

## Quem entrega encomendas da Huaya Express

O registro da Huaya não informa a entregadora nem um segundo código, salvo a DHL nas leituras copiadas em uma rota pela Alemanha, a J&T Express nas leituras copiadas para o México e a Early Bird na linha sueca. O site tampouco esclarece, pois suas páginas para vendedores se contradizem: a página da rota para o Reino Unido termina com USPS, e a da Austrália fala em voo para os EUA com entrega pela USPS. A transportadora final é a indicada pelo vendedor, então peça ao vendedor o nome dela e o respectivo código. Sobre taxas de importação, veja [encomendas retidas na alfândega](guide:customs).

## Encomenda Huaya Express que não chega ou rastreamento parado

**Última linha `It has been handed over to the service provider.`** O rastreador da Huaya pode não acrescentar nada depois: as linhas posteriores conhecidas são de uma transportadora nos EUA, de `Arrived at Origin Hub` até `Delivered`. O pacote agora segue sob o código próprio da transportadora local: peça ao vendedor o nome e o código dela para acompanhar lá. Veja mais em [rastrear encomendas da China](guide:tracking-from-china).

**Parada após a liberação aduaneira de importação.** A alfândega liberou e o pacote aguarda coleta pela transportadora local. Se passarem vários dias sem a linha de repasse, avise o vendedor. Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Sem resultado.** O Peek interpreta a resposta da Huaya para códigos desconhecidos (acima) como envio listado ainda sem leitura: continua verificando e mostra "Aguardando Huaya Express", sem pedir conferência do código. Confira o código na mensagem do vendedor por conta própria: HYYT, doze dígitos e GZ. Se estiver correto e continuar sem dados, peça ao vendedor que confirme o envio com a Huaya.

**Atraso, extravio ou devolução.** Os termos de reclamação da Huaya destinam-se às lojas contratantes, não aos destinatários. Peça reembolso ou reenvio ao vendedor e, se ele não responder, abra disputa no marketplace. Veja [encomendas extraviadas](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender) e [encomenda entregue mas não recebida](guide:delivered-not-received).

## Telefone e atendimento da Huaya Express

Canais listados em hyytgz.com, todos em chinês:

- **Telefone:** 18039073131, chamado de linha de atendimento da empresa (公司服务热线), um celular chinês (disque +86 ligando de fora da China continental). Sem horários nem idiomas informados.
- **E-mail:** 2065332407@qq.com, conta QQ Mail indicada como e-mail da empresa (公司邮箱).
- **Formulário:** a página 投诉建议 (reclamações e sugestões) pede nome, celular, e-mail e mensagem.
- **Localização:** distrito de Baiyun, Guangzhou (广州市白云区), conforme o rodapé das páginas.

As páginas 关于我们 (sobre nós) e 服务网点 (filiais) contêm apenas o título, e nenhuma página é voltada a destinatários. Comece pela loja para qualquer assunto do pedido.

## Rastrear uma encomenda Huaya Express com o Peek

Cole um código HYYT…GZ ou um link do rastreador da Huaya com o código após `documentCode=`, e o [Peek](/) associa à Huaya Express na hora: no catálogo do Peek esse formato pertence somente à Huaya. Uma referência de pedido da loja não tem formato próprio, mas o Peek acompanha mais de 4.500 transportadoras: digite a referência e escolha Huaya Express em "Trocar" (buscar 华亚运通 ou GZHY também encontra). Em encomendas já adicionadas, use "Trocar transportadora". O Peek passa então a exibir também o número pelo qual a Huaya rastreia o pedido. Como o Peek remove hifens, consulte referências com hifens no próprio rastreador da Huaya.

O Peek busca atualizações no próprio rastreador da Huaya: cada linha no texto original com localidade quando houver, mais o país de destino. A linha de etiqueta e `Shipment not yet received or processed` aparecem como "Etiqueta criada", a entrada no armazém como "Recebida pela transportadora", as demais linhas acima como "Em trânsito", e `Out for Delivery` e `Delivered` da transportadora americana como "Saiu para entrega" e "Entregue". Os horários são convertidos de Pequim para o fuso do seu aparelho (leituras copiadas da DHL e leituras em cidades dos EUA a partir do horário local da leitura; leituras copiadas da J&T Express no México a partir do horário da Cidade do México); em peektracker.com e nos aplicativos iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. Se o rastreador mostrar uma linha cujo horário ainda não chegou em Pequim, o Peek a mantém fora até essa hora. Ele também retém linhas gravadas mais de doze horas antes do horário exibido — ou mais de seis horas antes no mesmo segundo que uma linha assim — por serem previsões, não leituras: o histórico abre com "Outros sites podem mostrar mais etapas, mas são apenas previsões." até a entrega ou devolução, e "Ver" lista essas linhas em cinza com "Previsto para" e a data; elas nunca contam como leituras, não mudam status nem disparam alertas. A Huaya não fornece data prevista, então o Peek não mostra nenhuma.

Como o registro da Huaya não entrega ao Peek a transportadora final nem o código dela, o Peek permanece no rastreador da Huaya, cujo histórico termina no repasse ou nas leituras de uma transportadora americana até a entrega; apenas em rotas pela DHL na Alemanha ou para o México ele avança com as leituras copiadas da DHL ou da J&T Express. Para acompanhar o restante, adicione o código da transportadora local informado pelo vendedor como segunda encomenda.

O Peek classifica as duas linhas de alfândega em inglês como em trânsito — pois relatam desembaraço concluído, não retenção —, mas a linha em chinês `航班落地，清关中` (pousou, em desembaraço) como "Na alfândega". Assim, entre as linhas próprias da Huaya, "Etapas importantes" (entrega, retirada, alfândega, tentativas frustradas e devoluções) avisa apenas nessa etapa, enquanto "Só a entrega" permanece em silêncio. Já as leituras copiadas da DHL, da J&T Express e da transportadora nos EUA disparam alertas normalmente, incluindo saída para entrega e entrega em "Só a entrega".

## Perguntas frequentes sobre o rastreamento Huaya Express

### O que é a Huaya Express (华亚运通)?

É a marca da 华亚运通国际货运代理有限公司 (Huaya Yuntong International Freight Forwarding), agente de cargas de Guangzhou que despacha para lojas online. Seu site diz usar principalmente China Post, linhas próprias com impostos incluídos para EUA e Europa, linhas para itens sensíveis e as quatro grandes expressas. Seus conhecimentos têm HYYT, doze dígitos e GZ.

### Por que meu rastreamento da Huaya parou em "It has been handed over to the service provider."?

Porque a parte da Huaya termina aí: uma transportadora no seu país assumiu o pacote sob código próprio, e a linha da Huaya não informa o nome dela. O vendedor pode informar a transportadora e o código.

### Quem devo procurar se uma encomenda Huaya Express sumir?

A loja onde você comprou: ela é a cliente da Huaya para esse envio, e os termos de reclamação da Huaya destinam-se a ela. A Huaya divulga contatos apenas em chinês.

:::sources
- [Huaya Express: 华亚运通国际货运代理有限公司 (home)](http://hyytgz.com/) – razão social e logotipo, lojas atendidas, serviços principais, links 物流跟踪 (rastreador em chinês e inglês e 17TRACK), telefone, e-mail da empresa e bairro Baiyun
- [Huaya Express: 欧洲双清包税小包 (Europe and UK lines)](http://hyytgz.com/ywfw.php?id=21) – rotas da Europa e Reino Unido com texto dos EUA terminando em USPS e termos de reclamação para remetentes
- [Huaya Express: 澳大利亚专线小包 (Australia line)](http://hyytgz.com/ywfw.php?id=27) – rota da Austrália voando para os EUA via USPS e termos de reclamação para remetentes
- [Huaya Express: 投诉建议 (complaints and suggestions)](http://hyytgz.com/message.php) – campos do formulário
- [Huaya Express: 关于我们 (about us)](http://hyytgz.com/about.php) – sem conteúdo
- [Huaya Express: 服务网点 (branches)](http://hyytgz.com/outlets.php) – sem conteúdo
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nome em inglês, endereço e links do rastreador, formato HYYT, textos e significados das leituras (inclusive transportadora nos EUA até `Delivered` e linha antes do ponto de coleta), horário de Pequim (horário local para DHL e cidades dos EUA e Cidade do México para J&T Express), linhas futuras, resposta para código desconhecido, busca por referência, ausência de entregadora nomeada e o que o Peek exibe
:::
