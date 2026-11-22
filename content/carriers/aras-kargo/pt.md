---
title: Rastreio Aras Kargo: números e estados em português
description: Rastreia uma encomenda Aras Kargo: formato do número de 13 dígitos, estados turcos como TESLİMAT ŞUBESİNDE explicados em português e no Peek.
slug: rastreio-aras-kargo
published: 2026-11-22
updated: 2026-11-22
---

A Aras Kargo (também escrita Aras Cargo) é uma transportadora de encomendas na Turquia. O seu rastreio, tal como o Peek o lê, apresenta cada etapa em turco. A loja ou o remetente dá-te o número de seguimento: cola-o na caixa acima. O rastreio oficial da Aras Kargo fica em kargotakip.araskargo.com.tr. As tabelas abaixo explicam os estados em turco em português.

## Número de seguimento Aras Kargo: como é

O código do Peek reconhece os números da Aras Kargo como códigos de 13 dígitos. Os que liga automaticamente à Aras começam por 60 ou 65. Exemplos inventados:

| Número (inventado) | O que é |
| --- | --- |
| `6048291736542` | 13 dígitos começados por 60: o formato que o Peek associa à Aras Kargo. Outras transportadoras, incluindo [Delhivery](carrier:delhivery), Econt, Ozon Logistics, Paquetexpress, Correos Chile, Yunda e Korea Post, também usam 13 dígitos. |
| `6548291736542` | 13 dígitos começados por 65: o mesmo. |
| `1482917365482` | 13 dígitos começados por outros algarismos: o Peek não os liga por defeito à Aras, mas o rastreio da Aras aceita 13 dígitos. Se a loja indicar Aras Kargo, seleciona Aras Kargo manualmente (ver abaixo). |
| `604829173654` | Apenas 12 dígitos. Se escolheres Aras Kargo para este número, o Peek indica que «não emite números como este». Confirma se falta algum dígito. |

Os espaços não interferem ao colar o número no Peek. Uma ligação para o rastreio da Aras com o código (kargotakip.araskargo.com.tr com `code=` e o número) também funciona.

**Onde encontrar.** No email de envio da loja ou na página da encomenda. Se não o encontrares, consulta [como encontrar o número de seguimento](guide:find-tracking-number) e [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de rastreio Aras Kargo em português

O rastreio da Aras apresenta o estado atual (`Son Durum`), um dia previsto de entrega e a lista de movimentos de veículos (`Araç Hareketleri`). Cada movimento inclui data e hora (`İşlem Tarihi`), província (`İl`), unidade (`Birim`, delegação ou centro de transferência) e descrição (`İşlem Açıklaması`). Quando acontece, lista também os dias sem entrega e qualquer redirecionamento para outra delegação.

**Estado atual.** Um destes textos no topo:

| A Aras indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | Na delegação de origem que recebeu a encomenda. | «Aceite pela transportadora» |
| `YOLDA` | A caminho entre centros de transferência da Aras. | «Em trânsito» |
| `TESLİMAT ŞUBESİNDE` | Na delegação de entrega que leva a encomenda até ti. Mantém-se assim também após uma tentativa falhada. | «Em trânsito» |
| `TESLİM EDİLDİ` | Entregue. A Aras indica dia e hora (se a hora faltar, o estado mantém-se «Entregue» sem registo de entrega). | «Entregue» |

**Movimentos de veículos.** Cada registo ao carregar, descarregar ou ensacar:

| A Aras indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Carregada num veículo numa delegação. | «Em trânsito» |
| `ŞUBEDE ARAÇ İNDİRME` | Descarregada de um veículo numa delegação. | «Em trânsito» |
| `AKTARMADA HAT ARACI YÜKLEME` | Carregada num camião de longo curso num centro de transferência. | «Em trânsito» |
| `AKTARMADA HAT ARACI İNDİRME` | Descarregada de um camião de longo curso num centro de transferência. | «Em trânsito» |
| `AKTARMADA SUBE ARACI YUKLEME` | Carregada num veículo de delegação num centro de transferência. | «Em trânsito» |
| `AKTARMADA SUBE ARACI INDIRME` | Descarregada de um veículo de delegação num centro de transferência. | «Em trânsito» |
| `AKTARMADA TORBA DOLDURMA` | Colocada num saco com outras encomendas num centro de transferência. | «Em trânsito» |

O rastreio escreve as duas linhas com `SUBE` sem letras turcas, exatamente como acima.

**Entrega não efetuada** (`Teslim Edilememe Nedeni`, motivo). Uma linha por dia afetado:

| A Aras indica | O que significa para ti | O Peek mostra |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | A encomenda não pôde ser entregue nesse dia. | «Tentativa de entrega» |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Sem entrega devido a feriado. | «Tentativa de entrega» |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | O estafeta passou e deixou aviso. | «Tentativa de entrega» |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | A tua morada fica fora da zona de entrega da delegação. | «Tentativa de entrega» |

**Redirecionamento.** `YÖNLENDİRİLDİ` indica que a encomenda seguiu para outra delegação. A Aras apresenta o motivo e o Peek junta-o entre parênteses depois da palavra. O Peek mostra «Em trânsito».

Estes dois últimos tipos de linha têm apenas data, sem hora. O rastreio da Aras pode mostrar ainda uma nota livre do estafeta junto a uma tentativa falhada, que o Peek omite. Mais sobre as etapas em [o que significam os estados de seguimento](guide:tracking-statuses).

## Entrega da Aras Kargo na Turquia

A encomenda segue da delegação da Aras que a recebe (`ÇIKIŞ ŞUBESİNDE`), passa pelos centros de transferência (as linhas com `AKTARMADA`), até à delegação de entrega (`TESLİMAT ŞUBESİNDE`), que a entrega na tua morada.

**Dia previsto de entrega.** O rastreio da Aras fornece uma data. O Peek mostra-a como data estimada enquanto a encomenda está em trânsito e esse dia não fica atrás do último registo. Retira-a após uma entrega falhada ou depois de o dia passar.

**Fora da área de entrega.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` significa que a delegação não entrega na tua morada: pergunta à loja o que acontece a seguir.

**O que esta página não pode dizer.** O Peek não conseguiu ler as páginas do site da Aras Kargo, pelo que esta página não indica prazos de entrega, número de tentativas, períodos de guarda nem regras de levantamento. O rastreio da Aras, tal como o Peek o lê, não indica ponto de recolha, peso nem nome de serviço. Para uma entrega falhada, consulta [entrega falhada](guide:missed-delivery) e [encomenda não levantada](guide:uncollected-parcel).

## Rastreio Aras Kargo parado, perdido ou devolvido

**Sem resultado.** Quando a Aras não reconhece o número, o rastreio responde `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (não foi encontrado nenhum resultado). A loja pode ainda não ter entregue a encomenda. O Peek continua a perguntar: uma encomenda guardada com Aras Kargo mostra «À espera de Aras Kargo». Confirma os 13 dígitos com a loja; se nada surgir após alguns dias, pergunta-lhe se entregou mesmo a encomenda à Aras.

**Parada em `TESLİMAT ŞUBESİNDE`.** A delegação de entrega tem a encomenda e o estado mantém-se igual após uma tentativa falhada. Se existir um motivo para um dia (tabela acima), a entrega não foi concluída nesse dia e o motivo explica porquê. Caso contrário, aguarda distribuição. Como o rastreio da Aras não tem uma etapa de saída para entrega, o Peek mostra «Em trânsito» até à entrega ou a uma tentativa falhada.

**Dias sem atualizações.** Após quatro dias sem novos registos numa encomenda em movimento, o Peek mostra na tua lista o dia do último registo («Sem atualizações desde …») e no ecrã da encomenda «É habitual uma encomenda ficar sem novidades em voos longos, na alfândega ou ao fim de semana. O Peek continua a perguntar a Aras Kargo.», com a ligação «Pede ajuda a Aras Kargo» para a página de contactos apenas nesse aviso. Ao fim de semana é comum não haver novidades. Ver [porque o rastreio não atualiza](guide:tracking-not-updating).

**Marcada como entregue mas não chegou.** Confirma com vizinhos e com quem vive contigo. O Peek apresenta o nome ocultado de quem recebeu a encomenda (`Teslim Alan`) tal como aparece no resumo da Aras; podes também consultar diretamente o rastreio da Aras. Depois contacta a loja e a Aras. Ver [entregue mas não recebida](guide:delivered-not-received).

**Perdida ou devolvida.** O Peek ainda não tem mapeada uma mensagem de devolução específica da Aras. Como foi a loja que contratou o transporte, pede primeiro à loja um reembolso ou novo envio. Ver [encomenda perdida](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Apoio ao cliente e contacto Aras Kargo

O Peek não conseguiu ler o site da Aras Kargo, por isso esta página não indica telefone nem email: o Peek só lista contactos lidos diretamente nas páginas oficiais da transportadora.

Numa compra online, fala primeiro com a loja que reservou o envio. No Peek, o local de cada movimento indica a delegação ou o centro de transferência, e o local de entrega identifica a delegação que entregou: fornece esse nome ao pedir ajuda.

## Seguir uma encomenda Aras Kargo com o Peek

O [Peek](/) não consegue ter a certeza de um número Aras Kargo só pelo formato, pois outras transportadoras também usam 13 dígitos. Cola-o na mesma: o Peek acompanha mais de 4500+ transportadoras.

- **13 dígitos começados por 60 ou 65.** O Peek pergunta em simultâneo à Aras Kargo e às outras transportadoras compatíveis com esse padrão de 13 dígitos (como [Delhivery](carrier:delhivery), Econt, Ozon Logistics e Paquetexpress); se a pesquisa for feita a partir da Turquia, consulta primeiro a Aras Kargo. Enquanto verifica, vês «A verificar serviços de seguimento…». Se apenas a Aras Kargo confirmar a encomenda, o Peek diz «Aras Kargo tem esta encomenda.» e associa-a lá; se mais de uma a conhecer, o Peek pergunta-te qual escolher.
- **Outros 13 dígitos ou ainda sem resultado.** Muda de transportadora e escolhe Aras Kargo (escreve Aras) para o Peek consultar diretamente o rastreio da Aras. Numa encomenda já adicionada, podes alterar a transportadora na respetiva página.
- **Ligação para o rastreio da Aras.** O Peek lê o código na ligação e identifica logo a Aras Kargo («Transportadora detetada»).

Adicionada sem transportadora, a encomenda mostra «Ainda sem histórico de seguimento» enquanto o Peek continua a consultar transportadoras e serviços de seguimento. Se um serviço de seguimento apontar a Aras Kargo como única transportadora com registos datados, o Peek associa-a à Aras.

**O que o Peek mostra.** O estado atual, cada movimento com hora e local (delegação ou centro de transferência e província), os dias sem entrega, redirecionamentos, o dia previsto de entrega, a hora e delegação de entrega, bem como os nomes ocultados do destinatário e de quem recebeu a encomenda (`Teslim Alan`) mostrados no resumo. Ficam de fora o nome completo escondido na página, o remetente, o código de entrega, a verificação de identidade, dados de pagamento e fatura, volume e número de volumes, barra de progresso, notas livres e separador de SMS. O Peek lê as horas da Aras na hora da Turquia e converte-as para o teu fuso horário (o histórico termina com «Horas no teu fuso horário:» seguido do nome). A ligação «Abrir o site de Aras Kargo» abre o número diretamente no rastreio da Aras.

Quando o rastreio da Aras não responde, o Peek consulta serviços de seguimento e mostra o histórico que estes devolvem, nas suas próprias palavras.

Enquanto mantiveres a encomenda aberta no ecrã — ou no dia da entrega com as notificações ligadas — o Peek consulta diretamente a Aras Kargo até de 10 em 10 minutos (sem ritmo de 2 minutos, pois a Aras não envia etapa de saída para entrega). Fora disso, e após dois dias sem novidades (12 horas enquanto estiver em «Etiqueta criada» ou «Aceite pela transportadora»), verifica de hora em hora. Só através de serviços de seguimento verifica de hora em hora, de 3 em 3 horas após um dia sem registos e até de 15 em 15 minutos no dia da entrega se tiveres a encomenda aberta no ecrã ou as notificações ligadas. Escolhe «Todos os registos», «Passos importantes» ou «Só a entrega». O Peek não pode contactar a Aras Kargo nem a loja por ti.

## Perguntas sobre o rastreio Aras Kargo

### O que significa TESLİMAT ŞUBESİNDE na Aras Kargo?

Significa «na delegação de entrega»: a delegação da Aras encarregada de entregar a encomenda na tua morada já a tem. O estado mantém-se igual após uma tentativa falhada: procura uma linha com motivo, que o Peek mostra como «Tentativa de entrega».

### O que significa YOLDA na Aras Kargo?

Significa «a caminho»: a encomenda segue entre centros de transferência da Aras em direção à delegação de entrega. O Peek mostra «Em trânsito».

### Como é um número de seguimento Aras Kargo?

Tem 13 dígitos no código do Peek. Os que começam por 60 ou 65, como `6048291736542`, são associados à Aras Kargo. Para outros números de 13 dígitos, escolhe Aras Kargo manualmente.

### Porque é que a Aras Kargo não encontra o meu número de seguimento?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` significa que a Aras ainda não tem resultado para esse número. A loja pode ainda não ter entregue a encomenda. Confirma os 13 dígitos, aguarda um dia e pergunta à loja.

### Como contactar a Aras Kargo?

Pergunta primeiro à loja que contratou a entrega. Como o Peek não conseguiu ler o site da Aras Kargo, esta página não indica telefone nem email.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – nomes, formato de 13 dígitos e prefixos 60 e 65, rastreio e ligação, secções e textos turcos com respetivas etapas, aviso sem resultados, dia previsto de entrega, hora da Turquia, campos lidos e ignorados pelo Peek
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras com o mesmo formato, reconhecimento automático e recurso a serviços de seguimento
:::
