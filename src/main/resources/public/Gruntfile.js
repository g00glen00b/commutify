(function(module) {
  'use strict';

  module.exports = function(grunt) {
    var pkg = grunt.file.readJSON('package.json');
    grunt.initConfig({
      cfg: grunt.file.readJSON('.gruntrc'),
      pkg: pkg,

      // Clean folders
      clean: {
        dist: {
          src: ['<%= cfg.paths.dist %>']
        }
      },

      // Preprocess AngularJS HTML templates directly into the $templateCache
      html2js: {
        options: {
          base: './',
          module: 'commutify.templates',
          htmlmin: {
            collapseWhitespace: true,
            removeComments: true,
            removeRedundantAttributes: true
          }
        },
        dist: {
          src: ['<%= cfg.sources.templates %>'],
          dest: '<%= cfg.dist.js.templates %>'
        }
      },

      // Less compilation
      less: {
        dist: {
          options: {
            paths: ['<%= cfg.paths.app %>'],
          },
          files: {
            '<%= cfg.dist.css.unminified %>': '<%= cfg.sources.less.main %>'
          }
        }
      },

      // Linting/hinting JavaScript files
      jshint: {
        options: {
          reporter: require('jshint-stylish'),
          jshintrc: true
        },
        dist: ['<%= cfg.sources.javascript %>']
      },

      // Injecting source files (CSS, JavaScript) into HTML pages
      injector: {
        options: {
          relative: true,
          addRootSlash: false,
          ignorePath: '<%= cfg.paths.public %>'
        },
        dev: {
          files: {
            '<%= cfg.sources.thymeleaf %>': [
              '<%= cfg.sources.angular.modules %>',
              '<%= cfg.sources.angular.constants %>',
              '<%= cfg.sources.angular.config %>',
              '<%= cfg.sources.angular.services %>',
              '<%= cfg.sources.angular.filters %>',
              '<%= cfg.sources.angular.factories %>',
              '<%= cfg.sources.angular.directives %>',
              '<%= cfg.sources.angular.controllers %>',
              '<%= cfg.dist.css.unminified %>',
              '<%= cfg.dist.js.templates %>'
            ]
          }
        }
      },

      // Injecting Bower dependencies (CSS, JavaScript) into HTML pages
      wiredep: {
        dist: {
          src: ['../templates/**/*.html'],
          options: {
            ignorePath: '<%= cfg.paths.public %>',
            overrides: {
              'angular': { main: 'angular.min.js' },
              'angular-resource': { main: 'angular-resource.min.js' },
              'angular-ui-router': { main: 'release/angular-ui-router.min.js' },
              'bootstrap': { main: ['dist/css/bootstrap.min.css', 'dist/js/bootstrap.min.js'] },
              'bootflatv2': { main: 'bootflat/css/bootflat.min.css' },
              'fontawesome': { main: 'css/font-awesome.min.css' },
              'jquery': { main: 'dist/jquery.min.js' },
              'lodash': { main: 'lodash.min.js' }
            }
          }
        }
      },

      // Execute tasks by watching files
      watch: {
        options: {
          atBegin: true
        },
        styles: {
          files: ['<%= cfg.sources.less.pattern %>'],
          tasks: ['less:dist', 'wiredep:dist', 'injector:dev']
        },
        scripts: {
          files: ['<%= cfg.sources.javascript %>'],
          tasks: ['jshint:dist', 'html2js:dist', 'wiredep:dist', 'injector:dev']
        }
      }
    });

    // Default task, automatically executed on build and file changes
    grunt.registerTask('default', [
      'jshint:dist', 'html2js:dist',
      'less:dist',
      'wiredep:dist', 'injector:dev'
    ]);

    // Watch task, necessary when developing in an environment that doesn't support Maven
    grunt.registerTask('watch', [
      'jshint:dist',
      'watch:styles', 'watch:scripts'
    ]);

    require('load-grunt-tasks')(grunt);
    require('time-grunt')(grunt);
    require('jit-grunt')(grunt);
  };
}(module));
