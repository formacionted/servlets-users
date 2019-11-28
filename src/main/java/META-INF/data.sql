INSERT INTO `ted`.`usuario` (`id`, `email`, `password`) VALUES ('1', 'usuario1', 'admin');
INSERT INTO `ted`.`usuario` (`id`, `email`, `password`) VALUES ('2', 'usuario2', 'admin');

INSERT INTO `ted`.`todo` (`id`, `description`, `done`, `usuario_id`) VALUES ('1', 'clean the house', 0, '1');
INSERT INTO `ted`.`todo` (`id`, `description`, `done`, `usuario_id`) VALUES ('2', 'programming hard', 1, '1');
INSERT INTO `ted`.`todo` (`id`, `description`, `done`, `usuario_id`) VALUES ('3', 'test the application', 1, '2');

