---
title: Rastreamento WanbExpress: códigos WNB…YQ e quem entrega
description: Rastreie um pacote da WanbExpress (Wanb Express) vindo da China: formato do código WNB…YQ, significado dos status e quem contatar quando ele parar.
slug: rastreamento-wanbexpress
published: 2026-11-24
updated: 2026-11-24
---

A WanbExpress (também chamada de Wanb Express, WanBang Express ou WANB) é uma transportadora chinesa que despacha pacotes para o exterior, onde uma transportadora local conclui a entrega. Se uma loja enviou seu pedido pela WanbExpress, o código começa com `WNB` e termina com `YQ`: cole-o na caixa acima. O rastreador da própria WanbExpress, em tracking.wanbexpress.com, também pode informar qual transportadora faz a entrega no seu país e o código usado por ela.

## Código de rastreamento WanbExpress: como reconhecer

Os códigos da WanbExpress têm `WNB`, duas letras, dez dígitos e `YQ`, somando 17 caracteres. Com dígitos inventados:

| Código | O que é |
| --- | --- |
| `WNBKG4829105736YQ` | `WNB`, duas letras, dez dígitos e `YQ`: código da WanbExpress. |
| `WNBKG482910573YQ` | Apenas nove dígitos: não segue o formato da WanbExpress. Verifique se falta um dígito. |
| `JSEKG4829105736YQ` | Também termina em `YQ`, mas começa com `JSE`: código da [JS Express](carrier:js-express). |
| `JXCKG4829105736YQ` | Começa com `JXC`: código da Juxi Express. |

Várias operadoras chinesas encerram seus códigos com `YQ`, cada uma com seu próprio prefixo de letras: `WNB` é o que identifica a WanbExpress. Espaços, traços e letras minúsculas não interferem ao colar o código no Peek.

**Onde encontrar.** A loja informa o código no e-mail de confirmação de envio ou na página do pedido. Se não conseguir achá-lo, veja [como encontrar o código de rastreamento](guide:find-tracking-number).

**Link de rastreamento.** Um link para o rastreador da WanbExpress terminado em `?trackingNumbers=` e um código abre diretamente aquele pacote. O código `WNB` é a parte depois do sinal de igual: copie-o dali para o Peek.

**Um segundo código.** O rastreador da WanbExpress também pode exibir o código usado pela transportadora que faz a entrega final. Esse código pertence à outra transportadora: consulte-o no site dela ou cole-o no Peek como um pacote separado.

## Status do rastreamento WanbExpress explicados

Os registros da WanbExpress aparecem em inglês, cada um com horário e, às vezes, local. Aqui estão eles na ordem em que o código do Peek os organiza:

| A WanbExpress mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Shipping Order Created.`, `Parcel Data Received` | A loja registrou o envio e transmitiu os dados. A WanbExpress ainda não está com o pacote. | "Etiqueta criada" |
| `Parcel received at Shenzhen Sorting Centre.` | O centro de triagem em Shenzhen, na China, recebeu o pacote: é o primeiro registro físico dele. | "Recebida pela transportadora" |
| `Parcel processed at Shenzhen Sorting Centre.`, `Parcel departure in Shenzhen Sorting Centre.` | Triado e despachado desse centro. | "Em trânsito" |
| `Customs declaration completed` | A declaração aduaneira do pacote foi concluída. A frase não especifica qual país e não equivale à liberação: esta aparece como `Custom clearance completed`. | "Em trânsito" |
| `Parcel departure in Transit Facility, Parcel Departure` | Saiu de uma unidade de trânsito. | "Em trânsito" |
| `Parcel is leaving Airport, Parcel Leaving Port` | Partindo no voo para o país de destino. | "Em trânsito" |
| `Parcel arrives at Airport, Parcel Arrival` | Pousou no seu país ou nas proximidades. A etapa seguinte é a alfândega. | "Em trânsito" |
| `Custom clearance completed` | A alfândega de importação liberou a encomenda. | "Em trânsito" |
| `Parcel received by in JFK01.` | A WanbExpress registra como recebimento para o trecho final, mas o texto omite quem recebeu. | "Em trânsito" |
| `Your package is being prepared by the sender and will be handed over to us soon`, `Your parcel will soon be handed over to us! It is being prepared by the sender.`, `Order information received. We're expecting your parcel to arrive with us.` | Aviso de uma parceira com texto próprio, repassado sem código da WanbExpress: o pacote ainda não foi entregue a ela. | "Etiqueta criada", ou uma etapa posterior se algum registro já tiver movido o pacote |
| `In transit along Poste Italiane’s logistic network` | A caminho pela rede da transportadora entregadora — neste caso, a [Poste Italiane](carrier:poste-italiane). | "Em trânsito" |
| `Parcel is out for delivery.` | Em rota de entrega. | "Saiu para entrega" |
| `Consignee absent, Consignee could not be found.` | A tentativa da parceira local não encontrou ninguém. | "Tentativa de entrega" |
| `Placed in Parcelshop/Parcel locker` | Depois da tentativa sem sucesso, a parceira deixou o pacote em um ponto de retirada ou armário para você buscar. | "Pronta para retirada" |
| `Parcel has been delivered.(Front door)` | Entregue. `(Front door)` indica o local onde foi deixado. | "Entregue" |

**Registros da transportadora local.** Depois do voo, o rastreador da WanbExpress repassa os eventos da própria transportadora entregadora nas palavras dela, quase sempre sem um código interno da WanbExpress. Em um pacote destinado à Alemanha, o histórico da WanbExpress mostra a [iMile](carrier:imile) entregando, com cada evento da iMile precedido pelo nome do centro ou estação entre 【colchetes】:

| A iMile mostra | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `Received.` | O centro da iMile no país de destino recebeu o pacote. | "Em trânsito" |
| `Arrived.` | Chegou à estação de entrega que atende sua região. | "Em trânsito" |
| `Dispatch task assigned to DA.` | Um entregador (DA, delivery associate) foi designado para a rota, o que pode ocorrer horas antes da saída. | "Em trânsito" |
| `Shipment out for delivery.` | Em rota de entrega. | "Saiu para entrega" |
| `<Route not completed>`, `Back To DS.` | A rota terminou sem entrega e o pacote retornou à estação de entrega (DS). | "Tentativa de entrega" |
| `Scheduled to <2026-11-02> .` | Dia agendado para a próxima tentativa, após o retorno à estação. | Ainda "Tentativa de entrega" |

Eventos de outras parceiras chegam da mesma forma. Em envios para os Estados Unidos, `Parcel received by Transit Facility.` marca o recebimento pela parceira de última milha em uma unidade de trânsito, `In Processing at Transit Facility.` a triagem após o recebimento, `In Transit to Next Facility.` o deslocamento até a unidade entregadora e `Parcel received by local Facility.` a entrada nessa unidade antes da rota. `It is currently in transit to the next facility` e `The package is being transferred to the next facility` indicam transferências entre centros de uma parceira depois do voo, e `Shipment arrived to sorting centre / Shipment in the sorting centre` mostra a chegada ao centro de triagem de uma parceira antes da rota de entrega. O Peek exibe todos esses sete eventos como "Em trânsito". Em um pacote para o Reino Unido, eventos da [InPost](carrier:inpost) podem aparecer antes mesmo de o centro de triagem da WanbExpress registrar o pacote: `Your parcel is on its way to InPost. Please check back for updates`, mostrado pelo Peek como "Etiqueta criada", e depois `We have your parcel. Please check back for updates`, como "Recebida pela transportadora". `We'll apply your My Account preferences to this parcel delivery` é um aviso da InPost de que as preferências da conta do destinatário serão aplicadas, não uma movimentação: o Peek mantém o aviso no histórico sem alterar a etapa. O mesmo vale para `Consignee Contacted`, ligação do entregador da parceira registrada no mesmo minuto de uma tentativa em que ninguém atendeu.

**Horários.** Cada registro vem acompanhado do deslocamento em relação ao UTC. O Peek lê os horários assim e mostra tudo no fuso horário do seu aparelho, do mais recente ao mais antigo. Para entender os intervalos e repasses pelos quais passam as encomendas da China, veja [como rastrear encomendas da China](guide:tracking-from-china).

## Quem entrega pacotes da WanbExpress

Os registros da WanbExpress acompanham o pacote na saída da China, na alfândega e até uma transportadora local, que faz a entrega. O rastreador da WanbExpress pode mostrar o nome dessa transportadora, o código dela para o pacote e uma foto da entrega. O Peek não exibe a foto. Quando consegue identificar a quem pertence o código da transportadora local, o Peek exibe "Entrega por …" e acompanha essa transportadora; caso contrário, consulte o rastreador da WanbExpress ou pergunte ao vendedor.

**O que aparece no código do Peek.** Em um pacote para a Alemanha, o histórico da WanbExpress mostra a iMile entregando, e uma movimentação da WanbExpress menciona a rede da Poste Italiane. Em um pacote para o Reino Unido, surgem os eventos da InPost.

**Tentativas de entrega e retirada.** Valem sempre as regras de quem faz a entrega final: número de tentativas, emissão de aviso, local de retirada e prazo de guarda. O Peek não encontrou regras da WanbExpress para nenhum desses pontos. Assim que souber a transportadora local e o código dela, acompanhe pelo site dessa empresa e pergunte a ela como reagendar a entrega. Veja [entrega não realizada](guide:missed-delivery).

## Pacote WanbExpress atrasado ou rastreamento parado

**Código não encontrado.** Se o rastreador da WanbExpress não reconhecer o código, o Peek mostra "Aguardando WanbExpress" e continua tentando. Se depois de dois dias a WanbExpress continuar sem reconhecer o código e nenhum outro serviço tiver retorno, o Peek exibe "Depois de dois dias, a transportadora ainda não reconhece este código. Confira o código, escolha outra transportadora ou exclua a encomenda." Revise o código caractere por caractere na mensagem do vendedor: `WNB`, duas letras, dez dígitos e `YQ`. Se nada aparecer após alguns dias, confirme com o vendedor se o envio foi realmente feito.

**Parado em `Shipping Order Created.` ou `Parcel Data Received`.** A loja reservou o envio, mas a WanbExpress ainda não registrou a entrada física do pacote. Pergunte à loja quando ele foi entregue à transportadora.

**Parado após `Parcel arrives at Airport, Parcel Arrival`.** O pacote provavelmente aguarda o desembaraço aduaneiro. Na entrada da União Europeia (como Alemanha ou Itália), compras online pagam € 3 de imposto aduaneiro por tipo de produto em pacotes de até € 150 e, desde 1º de novembro de 2026, uma taxa de manuseio da União de € 2 por tipo de produto, qualquer que seja o valor. Se pedirem pagamento de encargos de importação, veja [encomendas paradas na alfândega](guide:customs); se ficar parado por muito tempo, avise o vendedor. Saiba mais em [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Nada após `Custom clearance completed`.** A transportadora entregadora já está com o pacote ou está prestes a recebê-lo, e o rastreamento próprio dela pode estar mais adiantado que o da WanbExpress. Procure o nome e o código dela no rastreador da WanbExpress ou pergunte ao vendedor quem entrega e com qual número.

**Tentativa de entrega.** Depois de um aviso como `<Route not completed>`, cabe à transportadora local definir a próxima tentativa e onde o pacote fica guardado. Fale com ela, não com a WanbExpress.

**Entregue, mas não chegou.** Procure a foto de entrega no rastreador da WanbExpress e depois fale com a transportadora entregadora e com o vendedor. Mais detalhes em [entregue, mas não recebido](guide:delivered-not-received).

**Extraviado ou devolvido.** Como o vendedor contratou o envio com a WanbExpress, é a ele — ou ao marketplace — que você deve pedir reembolso ou reenvio. Veja [encomenda extraviada](guide:lost-parcel) e [encomenda devolvida ao remetente](guide:returned-to-sender).

## Telefone e atendimento da WanbExpress

O Peek não conseguiu verificar o site da WanbExpress, por isso esta página não lista dados de contato da empresa. Quem contratou o frete com a WanbExpress foi o vendedor, então fale com ele sobre seu pedido — ou com o marketplace se ele não responder. Para assuntos ligados à entrega final, fale com a transportadora local.

## Como rastrear um pacote WanbExpress no Peek

O [Peek](/) reconhece um código da WanbExpress pelo formato: `WNB`, duas letras, dez dígitos e `YQ`, que nenhuma outra transportadora do catálogo utiliza, e consulta o rastreador da WanbExpress usando apenas esse número. Não precisa de conta, CEP nem telefone.

O rastreador da WanbExpress só responde depois que o reCAPTCHA do Google verifica que há uma pessoa consultando, então o Peek faz a consulta por navegador. Se o rastreador ainda assim não responder, o Peek recorre a serviços de rastreamento e mostra o histórico que eles guardam, nas palavras deles — um histórico que pode ficar atrás do da própria WanbExpress.

Quando consulta o rastreador da WanbExpress, o Peek exibe o status, cada registro com o local e os países de origem e destino informados no cadastro da WanbExpress. Se o cadastro marcar o pacote como em trânsito, saiu para entrega ou entregue, o Peek segue essa marcação mesmo que o texto da última linha diga menos. O Peek não mostra previsão de entrega.

O Peek omite as fotos de entrega e o endereço do destinatário que o rastreador da WanbExpress pode mostrar. Quando o rastreador informa o código da transportadora local e o Peek identifica qual é, o Peek pode mostrar "Entrega por …" e acompanhar o pacote no rastreamento dela; caso contrário, adicione o código dela ao Peek separadamente para ver seus eventos. O Peek acompanha mais de 4.500 transportadoras.

Enquanto o pacote estiver aberto, ou no dia da entrega com alertas ligados, e o rastreador da WanbExpress responder, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário da WanbExpress no dia em que o pacote sai para entrega (a cada 5 minutos quando a consulta precisa abrir navegador); fora disso, de hora em hora. Quando recorre a serviços de rastreamento, verifica de hora em hora, a cada 3 horas após um dia sem movimento, e até a cada 15 minutos no dia da entrega enquanto o pacote estiver aberto ou com alertas ligados. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode receber alertas para "Cada movimentação", "Etapas importantes" ou "Só a entrega". "Etapas importantes" (entrega, retirada, alfândega, tentativas sem sucesso e devoluções) inclui a saída para entrega, tentativas sem sucesso e a entrega, mas não as linhas de alfândega da WanbExpress, que o Peek classifica como "Em trânsito". O Peek não pode falar com a WanbExpress nem com o vendedor por você.

## Perguntas sobre o rastreamento WanbExpress

### O que é a WanbExpress?

É uma transportadora chinesa, também chamada de Wanb Express, WanBang Express ou WANB. No código do Peek, seus registros começam em um centro de triagem em Shenzhen, acompanham o voo e a alfândega e terminam com os eventos da transportadora local que entrega no país de destino.

### Onde posso rastrear um pacote da WanbExpress?

Na caixa acima ou no rastreador da própria WanbExpress, em tracking.wanbexpress.com. Esse rastreador usa o reCAPTCHA do Google para confirmar que você é uma pessoa e também pode indicar a transportadora local. Digite o código `WNB…YQ` completo.

### O que significa "Customs declaration completed" no rastreamento da WanbExpress?

Que a declaração aduaneira do pacote foi concluída. A frase não diz de qual país e não equivale à liberação aduaneira, que aparece como `Custom clearance completed`. O Peek exibe ambas como "Em trânsito".

### Quem entrega pacotes da WanbExpress no meu país?

Uma transportadora local, que o rastreador da WanbExpress pode identificar junto com o código usado por ela. O código do Peek mostra a iMile entregando em um pacote para a Alemanha e eventos da InPost em um pacote para o Reino Unido. Quando o Peek não consegue identificar a entregadora, veja no rastreador da WanbExpress ou pergunte ao vendedor.

### Quem devo contatar se meu pacote da WanbExpress sumir?

O vendedor de quem você comprou: foi ele quem contratou o frete com a WanbExpress. O Peek não conseguiu consultar o site da WanbExpress para listar dados de contato.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – nomes, formato do código WNB, o rastreador, sua verificação reCAPTCHA e o link que abre um pacote, textos dos eventos e suas etapas, os eventos da iMile repassados em um pacote para a Alemanha e sua linha da próxima rota, os eventos repassados da parceira de última milha dos EUA e de outras parceiras, os eventos da InPost repassados em um pacote para o Reino Unido e seu aviso de configurações da conta que não muda a etapa, o evento que cita a rede da Poste Italiane, horários com deslocamento UTC, o que o rastreador mostra (transportadora entregadora, o código dela, fotos de entrega, endereço do destinatário), o que o Peek guarda e omite, e os serviços de rastreamento a que recorre
- [Comissão Europeia: Reforma aduaneira da UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – imposto aduaneiro de € 3 por tipo de produto até € 150 desde 1º de julho de 2026 e taxa de manuseio da União de € 2 desde 1º de novembro de 2026
- [Comissão Europeia: Orientação sobre o imposto de € 3 e a taxa de manuseio da União (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – € 2 por tipo de produto assim como o imposto de € 3, qualquer que seja o valor
:::
