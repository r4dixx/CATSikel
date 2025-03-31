# CATS_Test_SIKEL

CATS - Technical Test 2025

Case study can be found in the repository: [docs/test_mobile_CA.pdf](test_mobile_CA.pdf)

### Module Graph

```mermaid
%%{
  init: {
    'theme': 'base',
    'themeVariables': {"primaryTextColor":"#fff","primaryColor":"#5a4f7c","primaryBorderColor":"#5a4f7c","lineColor":"#f5a623","tertiaryColor":"#40375c","fontSize":"12px"}
  }
}%%

graph LR
  :ui --> :core
  :ui --> :domain
  :app --> :core
  :app --> :data
  :app --> :domain
  :app --> :ui
  :data --> :core
  :data --> :domain
  :domain --> :core
```
