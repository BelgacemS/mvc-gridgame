# Makefile pour Java-MVC-GridGame


JAVAC = javac
JAVA = java
BIN_DIR = bin
MAIN_CLASS = app.Main
TEST_CLASS = test.TestAffichage
SOURCES = $(shell find . -name "*.java")
JAVAC_FLAGS = -Xlint:none

# Couleurs pour les messages
GREEN = \033[0;32m
NC = \033[0m


all: clean compile

compile:
	@echo "$(GREEN)Compilation du projet...$(NC)"
	@mkdir -p $(BIN_DIR)
	@$(JAVAC) $(JAVAC_FLAGS) -d $(BIN_DIR) $(SOURCES)
	@echo "$(GREEN)Compilation terminée !$(NC)"

run:
	@echo "$(GREEN)Lancement du jeu...$(NC)"
	@$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

test:
	@echo "$(GREEN)Lancement des tests...$(NC)"
	@$(JAVA) -cp $(BIN_DIR) $(TEST_CLASS)

clean:
	@echo "$(GREEN)Nettoyage des fichiers compilés...$(NC)"
	@rm -rf $(BIN_DIR)
	@find . -name "*.class" -type f -delete

.PHONY: all compile run test clean
