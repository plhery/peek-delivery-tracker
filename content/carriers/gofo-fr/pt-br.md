---
title: Rastreamento GOFO France: códigos GFFR, status e ajuda
description: Rastreie encomendas da GOFO na França: formatos GFFR e CIFR, significado dos status em francês, entregas sem sucesso e contato em caso de atraso ou extravio.
slug: rastreamento-gofo-franca
published: 2026-11-24
updated: 2026-11-24
---

A GOFO France é a rede francesa da GOFO, transportadora presente em vários países, responsável por entregar encomendas dentro da França. Na lista de transportadoras do Peek, ela também aparece como CIRRO Parcel France. Seus códigos de rastreamento começam com `GFFR` ou `CIFR`: acompanhe na página francesa de rastreamento da GOFO, em gofo.com, ou cole o código na caixa acima. Ainda não tem o número? Veja [como encontrar um código de rastreamento](guide:find-tracking-number).

## Código de rastreamento da GOFO: qual é o formato

Estes são os padrões que o Peek associa à GOFO France, em maiúsculas ou minúsculas:

| Formato | Exemplo fictício |
| --- | --- |
| `GFFR` e 13 dígitos | `GFFR4829173605841` |
| `GFFR` e 14 dígitos | `GFFR48291736058419` |
| `CIFR` e 13 dígitos | `CIFR4829173605841` |
| `CIFR` e 14 dígitos | `CIFR48291736058419` |

A própria referência da loja também pode valer como código: `PK` seguido de 20 dígitos, às vezes com hifens, como `PK-4829173605841926374-8` (fictício). O rastreamento da GOFO France responde a essas referências e as mantém vinculadas à etiqueta `GFFR` ou `CIFR` correspondente.

As outras redes da GOFO seguem a mesma lógica, mudando as letras do país após `GF` ou `CI`. Códigos `GFIT` ou `CIIT` pertencem à GOFO Itália, `GFNL` ou `CINL` à GOFO Países Baixos e `GFES` ou `CIES` à GOFO Espanha. `GFUS` com 14 dígitos, `GF` com apenas 13 ou `CR` com 12 pertencem à [GOFO Express](carrier:gofo) nos Estados Unidos, uma rede separada, e `GFCA` com 14 dígitos à GOFO Canadá. Nenhum deles é da GOFO France. Veja mais sobre como diferenciar códigos em [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da GOFO: o que significam em francês

O Peek consulta o rastreamento francês da GOFO, portanto os eventos aparecem nas palavras francesas da GOFO em qualquer idioma. Os mais comuns, na ordem em que costumam surgir:

| Texto da GOFO | O que significa para você |
| --- | --- |
| `Votre colis est en cours de préparation par l'expéditeur` | A loja está preparando a encomenda; a GOFO ainda não está com ela. |
| `Expédition en transit vers GOFO` | A caminho da GOFO, que ainda não a recebeu. |
| `Arrivé au site de prise en charge` | Chegou à unidade de recebimento da GOFO: primeira leitura física feita pela GOFO. |
| `Votre colis a été pris en charge au centre de tri` | Registrado em um centro de triagem da GOFO. |
| `Colis en cours d'acheminement` | Em trânsito entre unidades da GOFO. |
| `Votre colis est arrivé à l’agence régionale GOFO de destination` | Chegou à agência regional da GOFO que atende sua região e fará a entrega. |
| `Le chauffeur est en route pour la livraison` | Saiu para entrega com o motorista. |
| `Échec de la livraison, motif : Le destinataire n'est pas chez lui` | Entrega sem sucesso: ninguém estava em casa. |
| `Livré` | Entregue. |

As duas primeiras mensagens costumam gerar dúvida: até aparecer `Arrivé au site de prise en charge` ou a leitura do centro de triagem, a GOFO ainda não tem o pacote e o Peek o mostra como "Etiqueta criada". Quando entregue, aparece apenas `Livré`, em qualquer idioma: a linha original da GOFO pode trazer detalhes sobre quem recebeu, que o Peek omite.

O sistema da GOFO também classifica encomendas em categorias amplas. `Alerte` indica um problema sinalizado pela GOFO, exibido pelo Peek como "Precisa de atenção", e por si só não significa que houve tentativa de entrega. `Retourné` indica retorno ao remetente, exibido como "Devolvida ao remetente".

## Pacote da GOFO atrasado, entrega sem sucesso, extravio ou avaria

**Quanto tempo leva?** A GOFO afirma que a entrega costuma ocorrer de três a cinco dias úteis após a chegada à França, e em um a dois dias úteis depois de o rastreamento indicar "En cours de livraison" (termo da GOFO, não um status do Peek); a empresa não informa horário exato de entrega. Encomendas de fora da União Europeia passam antes pela alfândega e podem ficar sem movimentação até darem entrada em um depósito da GOFO. Sobre tarifas alfandegárias, IVA e a taxa de manuseio de € 2 por categoria de produto em vigor desde 1º de novembro de 2026, veja [encomendas paradas na alfândega](guide:customs).

**Sem movimentação.** O Peek continua consultando a GOFO. Se passarem quatro dias sem nova leitura antes de o pacote sair para entrega, a página da encomenda no Peek avisa e exibe um link para a ajuda da GOFO France ("Pedir ajuda a GOFO France"). A GOFO pede que você entre em contato se não houver atualização por mais de cinco dias úteis na França ou na UE. Veja também [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entrega sem sucesso.** Após `Échec de la livraison, motif : Le destinataire n'est pas chez lui`, o Peek mostra "Tentativa de entrega"; uma falha com outro motivo depois de `motif :` aparece como "Precisa de atenção". A GOFO faz até três tentativas de entrega; se falharem, guarda o pacote no depósito por 14 dias: entre em contato nesse prazo para combinar nova entrega ou o pacote volta ao remetente. A GOFO não oferece ponto de retirada, mudança de data nem devolução a pedido: para devolver algo que não quer mais, trate com a loja. Quando você não está, o motorista também pode deixar na caixa de correio ou com um vizinho: avise a GOFO antes se não quiser isso. Para alterar o endereço, use o formulário enviado por cs@fr.gofo.com antes de o pacote sair com o motorista; se não recebeu ou o link expirou, fale com a GOFO rapidamente. Veja o que conferir em [entregas sem sucesso](guide:missed-delivery).

**Atraso.** Pelas regras de consumo da UE, a loja precisa entregar em até 30 dias, salvo outro prazo combinado. Se não cumprir, conceda um prazo extra razoável; se ainda assim não entregar, você pode cancelar o pedido e receber seu dinheiro de volta.

**Marcado como `Livré` mas não chegou.** Primeiro verifique o comprovante de entrega na página da GOFO: ele informa onde o pacote foi deixado, às vezes com foto (que o Peek não exibe). Confira sua caixa de correio, pergunte aos vizinhos ou ao zelador e reporte logo à GOFO; fotos da fachada e da entrada da casa ou do prédio ajudam a agilizar a apuração, mas não são obrigatórias. A responsabilidade pela entrega é da loja: cabe a ela acionar a transportadora e provar a entrega — se não conseguir, deve reenviar o produto ou reembolsar você. Saiba mais em [marcado como entregue mas não recebido](guide:delivered-not-received).

**Danificado.** A loja responde por qualquer avaria desde a postagem até o recebimento por você, mesmo ocorrida no transporte. Sempre que possível, confira a embalagem na frente do motorista. Veja mais em [encomendas danificadas](guide:damaged-parcel).

## Atendimento ao cliente da GOFO France

A página de ajuda da GOFO France fica no site francês gofo.com, e o Peek direciona para ela como "Pedir ajuda a GOFO France" quando passam quatro dias sem nova leitura antes da saída para entrega. Os canais oferecidos aos destinatários são:

- **Telefone:** +33 9 70 70 90 65, de segunda a sexta, das 9h às 18h, exceto feriados;
- **E-mail:** cs@fr.gofo.com.

Fale com a GOFO sobre a entrega em si: comprovante de entrega, nova tentativa ou correção de endereço. Em caso de pedido atrasado, extraviado ou avariado, comece pela loja que enviou, pois ela responde pela entrega. Tenha em mãos o código `GFFR` ou `CIFR` e o número do pedido.

## Como rastrear uma encomenda da GOFO France com o Peek

O [Peek](/) reconhece códigos `GFFR` e `CIFR` automaticamente. Você também pode colar um link da página francesa da GOFO (em gofo.com ou gofoexpress.fr, contendo `id=` e o código). Uma referência `PK` também funciona: o Peek consulta a GOFO France e identifica a transportadora assim que a GOFO reconhece o número.

O Peek consulta o rastreamento francês da GOFO até a cada 10 minutos — e até a cada 2 minutos a partir das 6h (horário local) do dia em que a GOFO registra a saída para entrega — enquanto o rastreador da própria GOFO responde (por meio de serviços de rastreamento, até a cada 15 minutos enquanto o pacote estiver aberto ou com alertas ligados). Após dois dias sem novidades (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), o Peek passa a verificar de hora em hora. Ele exibe o status, o histórico com o local informado pela GOFO em cada leitura, o horário da entrega e esses pontos no mapa, podendo notificar em cada leitura, só em etapas importantes ou só no dia da entrega. Quando a GOFO informa apenas um código de depósito ou o país, o evento não exibe local. A GOFO registra cada evento no horário local e o Peek converte para o seu fuso; em peektracker.com e nos apps para iPhone e Android, a última linha sob o histórico informa o fuso ("Horários no seu fuso horário: …"). Até a GOFO publicar a primeira leitura, o Peek mostra "Aguardando GOFO France" e continua consultando em vez de dar o código como inválido de imediato. Se após dois dias a GOFO ainda não reconhecer o código e nenhuma outra fonte responder por ele, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." e passa a verificar diariamente (e semanalmente depois de uma semana desde o cadastro). Se a GOFO listar a remessa mas ainda sem leituras, ela já reconhece o número: o Peek mantém "Aguardando GOFO France" sem pedir para conferir o código. O Peek mostra a data prevista da GOFO até o pacote sair para entrega, ser entregue, devolvido ou ir para um ponto de retirada; não exibe peso e omite fotos de entrega e avisos da GOFO. Ele não pode falar com a GOFO nem com a loja por você.

## Perguntas sobre o rastreamento da GOFO

### O que significa "Expédition en transit vers GOFO"?

Significa que a encomenda está a caminho da GOFO, que ainda não a recebeu. A primeira leitura física da GOFO vem depois: `Arrivé au site de prise en charge` ou `Votre colis a été pris en charge au centre de tri`. Até lá, o Peek exibe o pacote como "Etiqueta criada".

### A GOFO é confiável?

Sim. A GOFO FRANCE é uma sociedade SAS francesa registrada no diretório Sirene do INSEE sob o Siren 978 559 839, ativa desde maio de 2023, com sede em Marly-la-Ville (Val-d'Oise).

### A GOFO é a mesma empresa que a CIRRO Parcel?

Na França, para rastreamento, sim: a lista de transportadoras do Peek traz CIRRO Parcel France como outro nome da GOFO France. Um rastreador que menciona apenas "Cirro" pode se referir à rede francesa, italiana ou holandesa da GOFO, ou à linha internacional da própria Cirro, que entrega pacotes aos correios nacionais sob os códigos deles.

### O que fazer se minha encomenda da GOFO extraviar?

Fale com a loja. Pelas regras da União Europeia, o vendedor responde pela entrega até o pacote chegar até você; se não puder provar a entrega, deve reenviar o item ou reembolsar você. Veja mais em [encomendas extraviadas](guide:lost-parcel).

:::sources
- [GOFO France: Service client](https://www.gofo.com/fr/contact-us/) – telefone, e-mail, horários e FAQ em francês: prazos de entrega, "En cours de livraison", sem horário exato, desembaraço aduaneiro, rastreamento parado, tentativas, depósito, caixa de correio ou vizinho, sem ponto de retirada/mudança de data/devolução, formulário de endereço e link expirado, comprovante de entrega e fotos para apuração
- [European Commission: EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – taxa de manuseio de € 2 desde 1º de novembro de 2026
- [INSEE: Avis de situation au répertoire Sirene](https://avis-situation-sirene.insee.fr/) – GOFO FRANCE, Siren 978 559 839, SAS, ativa desde 26 de maio de 2023, sede em Marly-la-Ville
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – entrega em até 30 dias, prazo extra e responsabilidade por avarias até o recebimento
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – prova de entrega a cargo da loja e conferência do pacote no ato do recebimento
:::
