---
title: Seguimento Mayi Yinfu: números MY, voos e entrega DHL
description: Segue uma encomenda Mayi Yinfu (蚂蚁银服) vinda da China continental: números MY…US e MYYF…CN, linhas de voo, etapas antecipadas e passagem para a DHL na Europa.
slug: seguimento-mayi-yinfu
published: 2026-11-21
updated: 2026-11-21
---

Mayi Yinfu (`蚂蚁银服`) é o nome curto da `广东蚂蚁银服国际物流有限公司` (*Mayi Yinfu International Logistics*), um transitário de Guangdong, na China continental, que expede pequenos pacotes e encomendas expresso para o estrangeiro. Os seus números de seguimento são compostos por `MY`, dez algarismos e `US` (ou `MYYF`, oito algarismos e `CN`): cola o teu na caixa acima ou escreve-o no campo `快递查询` em `mayiyinfu.com.cn`. A Mayi Yinfu transporta os volumes de avião até à tua região e entrega-os a uma transportadora local — para a Europa, a Deutsche Post [DHL](carrier:dhl).

## Número de seguimento Mayi Yinfu: como é

As letras `MY` vêm de `蚂蚁` (*mǎyǐ*, formiga). Estes são os formatos conhecidos pelo Peek, com algarismos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `MY4829105736US` | `MY`, 10 algarismos e `US` (14 caracteres): número próprio da Mayi Yinfu, detetado de imediato. |
| `MYYF48291057CN` | `MYYF` (iniciais de `蚂蚁银服`), 8 algarismos e `CN` (14 caracteres): segundo formato reconhecido pelo localizador da Mayi Yinfu. |
| `MY482910573US` | Apenas 9 algarismos em vez de 10: se nenhuma transportadora responder, o Peek avisa: «Começa como um número de seguimento de Mayi Yinfu, mas falta-lhe um carácter: pode estar mal escrito. Compara-o com a tua mensagem de envio.» |
| `RR482910576CN` | Duas letras, 9 algarismos e `CN`: um registo postal da [China Post](carrier:china-post), não da Mayi Yinfu. |

Espaços e minúsculas não interferem no Peek. Como a Mayi Yinfu trabalha apenas para empresas expedidoras, o código é fornecido pela loja onde compraste ([como encontrar o número de seguimento](guide:find-tracking-number)); o localizador da Mayi Yinfu também aceita a referência da encomenda do vendedor escrita exatamente como consta (incluindo traços).

## Estados de seguimento da Mayi Yinfu explicados

A Mayi Yinfu não publica uma lista oficial de estados. As suas próprias linhas vêm em chinês (quase sempre seguidas de tradução inglesa) e, após a entrega ao parceiro, copia em inglês os registos da transportadora final:

| Estado na Mayi Yinfu | O que significa para a tua encomenda | O Peek mostra |
| --- | --- | --- |
| `货物电子信息已经收到` | Dados eletrónicos recebidos na plataforma Hualei (`华磊`, também usada pela BYHD): pedido criado, ainda sem leitura física. | «Etiqueta criada» |
| `The instruction data for this shipment have been provided by the sender to DHL electronically` | Copiado da DHL: os dados da etiqueta foram enviados eletronicamente à DHL, sem entrega do volume. | «Etiqueta criada» |
| `已入库,到达收货点` | Entrada registada no armazém de receção: a Mayi Yinfu já tem o pacote (`进仓时间：…；Warehouse entry time:…` regista igualmente a entrada em armazém antes da exportação). | «Aceite pela transportadora» |
| `已出库,货物离开操作中心`, `国内清关完成` | Saída do centro operacional e desalfandegamento de exportação concluído na China continental. | «Em trânsito» |
| `货物已交机场` | Entregue no aeroporto de origem (**não** na tua morada, apesar do inglês *delivered to the airport*), seguido de `货物已交机场，等待安排航班中The goods have been delivered to the airport and are awaiting the arrangement of flights.` ou do dia do voo previsto. | «Em trânsito» |
| `航班延误` | Voo atrasado, com indicação do próximo dia previsto. | «Em trânsito» |
| `航班已起飞`, `头程起飞`, `头程落地`, `二程起飞` | Descolagem na origem (`航班已起飞`), partida e aterragem do primeiro voo (`头程起飞`, `头程落地`) e partida do voo de ligação (`二程起飞`, traduzido erradamente no localizador por *Flight 202*). | «Em trânsito» |
| `航班落地`, `航班落地，拆板清关The flight landed, and the board was dismantled and cleared` | Aterragem na região de destino e abertura das paletes para desalfandegamento de importação. | «Na alfândega» |
| `清关完成，待转运Clearance has been completed and it is awaiting transfer.`, `已转运transferred` | Alfândega concluída e entrega à transportadora final, cujo número passa a constar no registo. | «Em trânsito» |
| `The international shipment has been processed in the parcel center of origin`, `The international shipment has been processed in the export parcel center` | Copiados da DHL: tratamento nos centros de encomendas da DHL na Alemanha. | «Em trânsito» |
| `The shipment has arrived in the destination country/destination area.` | Chegada ao teu país (por vezes com uma ligação entre parênteses para o operador local). | «Em trânsito» |
| `The shipment has been loaded onto the delivery vehicle` | Carregada no veículo de distribuição final. | «Em distribuição» |
| `The shipment has been successfully delivered` | Entregue com sucesso. | «Entregue» |

**Linhas de script automático (`CO_C_CR`).** Isoladamente, as linhas `国内清关中` (alfândega de exportação), `已排航班` (voo marcado), `起运地航班起飞` (voo partido), `已到达目的国` (chegou ao país de destino) e `清关完成` (desalfandegado) vêm de um guião automático capaz de dar a encomenda no estrangeiro antes de chegar ao aeroporto: o Peek exclui-as ([o que significam os estados de seguimento](guide:tracking-statuses)).

## Quem entrega as encomendas da Mayi Yinfu?

O site da Mayi Yinfu não divulga parceiros, mas após `已转运transferred` o processo exibe o número de transferência. **Para a Europa**, trata-se de um número da Deutsche Post [DHL](carrier:dhl), seguido pela DHL até à entrega no teu país (a linha de chegada da DHL pode incluir uma ligação para a empresa que faz a entrega à tua porta). **Noutros destinos**, identifica o operador pelos [formatos de números de seguimento](guide:tracking-number-formats) ou pergunta ao vendedor. Tentativas de entrega, pontos de recolha e prazos de guarda seguem as regras da transportadora final; se te pedirem encargos aduaneiros, vê [encomendas retidas na alfândega](guide:customs), e perante SMS suspeitos consulta [mensagens falsas de entregas](guide:delivery-scam-texts).

## Encomenda Mayi Yinfu parada, desconhecida ou atrasada

- **Apenas as primeiras linhas:** `货物电子信息已经收到` (com ou sem a linha eletrónica da DHL) significa que o vendedor criou o envio mas ainda não entregou o pacote ao armazém. Passados alguns dias sem entrada em armazém, fala com a loja.
- **Parada após `已转运transferred`:** a parte da Mayi Yinfu terminou; acompanha agora o número da transportadora final ([seguir uma encomenda vinda da China](guide:tracking-from-china), [porque é que o seguimento não atualiza](guide:tracking-not-updating)).
- **Número desconhecido:** para um número que não conhece, o localizador da Mayi Yinfu devolve ainda assim um registo com esse número em `参考号` (referência) e todos os restantes campos em branco. O Peek lê isso como um envio registado sem leituras e mantém «À espera de Mayi Yinfu» sem pedir verificação do código: confere tu próprio cada carácter com a mensagem do vendedor.
- **Perdida, danificada ou devolvida:** a Mayi Yinfu não publica processo de reclamação; pede reembolso ou novo envio ao vendedor ou marketplace ([encomenda perdida](guide:lost-parcel), [devolvida ao remetente](guide:returned-to-sender), [entregue mas não recebido](guide:delivered-not-received)).

## Contactos e apoio ao cliente da Mayi Yinfu

O site `mayiyinfu.com.cn`, apenas em chinês, indica unicamente um número de telemóvel na China continental, **`+86 13705996996`** (`13705996996`), no cabeçalho como linha nacional (`全国服务热线`) e na caixa `在线咨询`. A página de contactos (`contact.html`), apresentação, aviso legal e privacidade estão vazios (sem morada, e-mail nem formulário). Contacta sempre primeiro a loja onde compraste.

## Seguir uma encomenda Mayi Yinfu com o Peek

Cola um número `MY…US` ou uma ligação `mayiyinfu.com.cn/trackIndex.htm` com `documentCode=` no [Peek](/): fica logo atribuído à Mayi Yinfu («Transportadora detetada»). Num número `MYYF…CN`, o Peek mostra «A verificar serviços de seguimento…» e depois «Mayi Yinfu tem esta encomenda.». Para uma referência do vendedor sem traços, toca em «Alterar» e escolhe a Mayi Yinfu (`蚂蚁银服` ou `MYYF` também a encontram; como o Peek remove traços, pesquisa referências com traços diretamente em `mayiyinfu.com.cn`).

**Leituras, relógios e etapas escritas com antecedência.** Quando uma linha de voo anuncia um dia futuro esperado, o Peek mostra apenas a etapa em inglês (como `The first leg has departed`) sem o dia ainda por chegar, e omite nomes, moradas, códigos postais, peso, taxas e fotos (sem data prevista de entrega; a sigla isolada `SZ`, código ISO de Essuatíni, é limpa do local). As leituras na China continental (`CN`) — e as linhas inglesas copiadas da DHL com código de país assim que a DHL assume o registo — são lidas na hora de Pequim (`UTC+8`) e convertidas para o teu fuso («Horas no teu fuso horário: …»); as restantes mantêm a hora local. Qualquer linha gravada mais de 12 horas antes da hora que exibe (ou mais de 6 horas antes no mesmo segundo) é tratada como previsão: antes dessa hora o Peek omite-a («O site de Mayi Yinfu mostra etapas escritas com antecedência, até mesmo a entrega. O Peek não as pode confirmar, por isso omite-as. Procura nesse site a transportadora que entrega no teu país e o respetivo número de seguimento; se não houver, pergunta ao vendedor.»); passada a hora, surge a cinzento sob «Escrito com antecedência por Mayi Yinfu» («…por isso mostra-as a cinzento…»), sem mudar o estado nem disparar alertas.

**Passagem automática para a DHL e frequência.** Quando o número de transferência pertence inequivocamente à [DHL](carrier:dhl), o Peek apresenta «Entrega: DHL» com esse segundo código (ou «Anunciada a DHL, ainda não recebida» enquanto a DHL só tiver os dados eletrónicos) e muda para o seguimento direto da DHL mal surja uma leitura da DHL pelo menos tão recente como a última da Mayi Yinfu; qualquer outro número de entrega deve ser adicionado à parte como nova encomenda. Com a encomenda aberta ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos, e de hora a hora nos restantes casos ou após 12 horas em «Etiqueta criada» / «Aceite pela transportadora» ou dois dias sem novidades (se ao fim de dois dias só existirem as linhas iniciais, consulta também serviços de seguimento). Alertas: «Todas as leituras», «Passos importantes» ou «Só a entrega».

## Perguntas frequentes sobre o seguimento da Mayi Yinfu

### Porque é que o meu número Mayi Yinfu termina em `US`?

Todos os números `MY` da Mayi Yinfu terminam em `US` (e os `MYYF` em `CN`), independentemente do país de destino: não significa que a encomenda vá para os Estados Unidos nem que seja da USPS.

### `货物已交机场` (*delivered to the airport*) quer dizer que a encomenda foi entregue?

Não: significa apenas que a Mayi Yinfu entregou a carga no aeroporto de partida para embarque no voo. A entrega na tua morada acontece mais tarde através da transportadora local.

### Quanto tempo demora uma entrega da Mayi Yinfu?

As páginas de serviços da Mayi Yinfu contêm apenas texto provisório sem prazos de trânsito: orienta-te pela estimativa indicada pela loja na compra.

### Quem devo contactar se uma encomenda Mayi Yinfu desaparecer?

O vendedor ou marketplace onde fizeste a encomenda: foi quem contratou a Mayi Yinfu, cujo site apenas indica um telemóvel na China continental.

:::sources
- [Mayi Yinfu: 广东蚂蚁银服国际物流有限公司 (página inicial)](http://www.mayiyinfu.com.cn/Default.aspx) – denominação social, pesquisa 快递查询, área empresarial e telemóvel +86 13705996996 (全国服务热线 / 在线咨询)
- [Mayi Yinfu: 联系我们 (contactos)](http://www.mayiyinfu.com.cn/contact.html) – página de contactos sem morada, e-mail nem formulário além do telemóvel
- [Mayi Yinfu: 国际小包 (pequenos pacotes internacionais)](http://www.mayiyinfu.com.cn/products/category-1.html) – texto de preenchimento sem prazos nem parceiros publicados
- [Universal Parcel Scraper: Mayi Yinfu](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/mayiyinfu) – plataforma Hualei, formatos MY…US e MYYF…CN, linhas de voo e CO_C_CR, hora de Pequim, filtro SZ e transição para a Deutsche Post DHL
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – deteção da China Post e DHL, etapas escritas com antecedência e serviços de seguimento
:::
